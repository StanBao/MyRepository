package com.by.store.service.impl;

import com.by.store.entity.Address;
import com.by.store.mapper.AddressMapper;
import com.by.store.service.IAddressService;
import com.by.store.service.IDistrictService;
import com.by.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;

/**新增收货地址的实现类*/
@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    //添加收货地址时  需要依赖收货地址的service

    @Autowired
    private IDistrictService districtService;

    //在配置文件中定义user.address.max-count=20 然后在此通过@value引入变量
    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
         //调用查询收货地址数量的方法
        Integer count = addressMapper.countByUid(uid);
        if (count >= maxCount){
            throw new AddressCountLimitException("用户收货地址超出上限");
        }

        //补全省市区
        String province = districtService.getNameByCode(address.getProvinceCode());
        String city = districtService.getNameByCode(address.getCityCode());
        String area = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(province);
        address.setCityName(city);
        address.setAreaName(area);
        //补全uid    isDelete
        address.setUid(uid);
//        如count是0 说明用户没有收货地址 那么接下来的一条地址就设置成默认 isDefault=1 说明是默认地址
//                isDefault=0说明不是默认地址
        Integer isDefault = count == 0? 1:0;
        address.setIsDefault(isDefault);
        //补全四项日志
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());
        //新增地址的方法
        Integer rows = addressMapper.insert(address);
        if (rows != 1){
            throw  new InsertException("未知错误");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        for (Address address : list) {
//             address.setAid(null);
//             address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setTel(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);
            address.setIsDefault(null);
        }
        return list;
    }

    @Override
    @Transactional
    public void setDefault(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result ==null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        //检测当前获取到的地址数据的归属
        if (!result.getUid().equals(uid)){
            throw new AccessDenieddException("非法数据访问");
        }
        Integer rows = addressMapper.updateNonDefault(uid);
        if (rows<1){
            throw new UpdateException("更新时异常");
        }
        Integer row = addressMapper.updateDefaultByAid(aid,username,new Date());
        if (row!= 1){
            throw new UpdateException("更新时异常");
        }
    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if(result == null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDenieddException("非法访问");
        }
        //在删除之前  先判断用户要删的是不是默认地址 0的话正常删除
        if (result.getIsDefault() == 0){
            Integer rows = addressMapper.deleteByAid(aid);
            if (rows!=1){
                throw new DeleteException("删除过程发生未知异常");
        }
        }
        //如果是1的话  先修改最新的地址为默认  在执行删除地址
        if (result.getIsDefault()==1){
            Address lastModifiedaddress = addressMapper.findLastModified(uid);
            Integer row = addressMapper.updateDefaultByAid(lastModifiedaddress.getAid(), username, new Date());
            if (row!= 1){
                throw new UpdateException("更新默认地址出错");
            }
            //设置最新地址为默认地址后  再执行删除用户指定的地址
          Integer rows =   addressMapper.deleteByAid(aid);
            if (rows!=1){
                throw new DeleteException("删除过程发生未知异常");
            }

        }

    }

    @Override
    public Address getByAid(Integer aid, Integer uid) {
        // 根据收货地址数据id，查询收货地址详情
        Address address = addressMapper.findByAid(aid);

        if (address == null) {
            throw new AddressNotFoundException("尝试访问的收货地址数据不存在");
        }
        if (!address.getUid().equals(uid)) {
            throw new AccessDenieddException("非法访问");
        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedUser(null);
        address.setCreatedTime(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        return address;
    }
}
