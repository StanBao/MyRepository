package com.by.store.mapper;

import com.by.store.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/** 收货地址持久层接口*/
public interface AddressMapper {
    /**
     * 插入用户地址数据
     * @param address 收获地址数据
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     * 根据id查询用户收货地址数量
     * @param uid 用户id
     * @return 当前用户收货地址的总数
     */
    Integer countByUid(Integer uid);

    /**
     * 根据id查询所有收货地址
     * @param uid  id
     * @return   收货地址的集合
     */
   List<Address> findByUid(Integer uid);


    /**
     * 根据aid查询收货地址
     * @param aid 收货地址id
     * @return 收货地址数据  没有就返回null
     */
  Address  findByAid(Integer aid);

    /**
     * 根据uid值来修改用户所有收货地址为非默认
     * @param uid 用户id
     * @return 影响行数
     */
  Integer updateNonDefault(Integer uid);

    /**
     * 根据aid 来更新默认收货地址
     * @param aid
     * @return
     */
  Integer updateDefaultByAid(@Param("aid") Integer aid, @Param("modifiedUser") String modifiedUser,@Param("modifiedTime") Date modifiedTime);


    /**
     * 根据aid删除收货地址
     * @param aid 收货地址id
     * @return 受影响行数
     */
  Integer deleteByAid(Integer aid);

    /**
     * 根据用户uid查询最新的收货地址
     * @param uid 用户id
     * @return  一条收货地址对象
     */
  Address findLastModified(Integer uid);
}
