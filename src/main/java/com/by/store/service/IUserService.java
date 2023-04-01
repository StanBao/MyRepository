package com.by.store.service;

import com.by.store.entity.User;

/* 用户模块业务层接口*/
public interface IUserService {
    /**
     * 用户注册方法
     * @param user  用户的数据对象
     */
    void reg(User user);


    /**
     * 用户登陆功能
     * @param username
     * @param password
     * @return 当前匹配的用户数据 如果没有返回null
     */
    User login(String username,String password);


    /**
     * 用户更改密码
     * @param uid
     * @param username
     * @param oldPassword
     * @param newPassword
     */
    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);


    /**
     * 根据用户id查询用户的数据
     * @param uid    用户id
     * @return  用户的数据
     */
    User getByUid(Integer uid);


    /**
     * 更新用户的数据
     * @param uid    用户id
     * @param username   用户名
     * @param user   用户对象数据
     */
    void changeInfo(Integer uid, String username,User user);

    /**
     * 修改用户的头像
     * @param uid 用户id
     * @param avatar  用户头像地址
     * @param username 用户名
     */
    void changeAvatar(Integer uid,String avatar,String username);


}
