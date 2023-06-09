package com.by.store.service;

import com.by.store.entity.Address;

import java.util.List;

/**收货地址业务层接口*/
public interface IAddressService {
    void addNewAddress(Integer uid, String username, Address address);

    List<Address> getByUid(Integer uid);

    /**
     * 修改用户某条地址为默认地址
     * @param aid 地址id
     * @param uid 用户id
     * @param username 用户名
     */
    void setDefault(Integer aid,Integer uid,String username);

    /**
     * 删除用户选中的收货地址
     * @param aid 地址id
     * @param uid 用户id
     * @param username  用户名
     */
    void delete(Integer aid,Integer uid,String username);

    /**
     * 根据收货地址数据的id，查询收货地址详情
     * @param aid 收货地址id
     * @param uid 归属的用户id
     * @return 匹配的收货地址详情
     */
    Address getByAid(Integer aid, Integer uid);
}
