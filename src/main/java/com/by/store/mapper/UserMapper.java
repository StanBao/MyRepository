package com.by.store.mapper;

import com.by.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/*用户模块的持久层接口*/

public interface UserMapper {
    /**
     * 插入用户的数据
     * @param user 用户的数据
     * @return 受影响的行数   （增删改都有受影响的行数 根据返回值行数判断是否成功）
     */
    Integer insert(User user);


    /**
     * 根据用户名查找用户的数据
     * @param username  用户名
     * @return  如果找到返回这个用户的数据，找不到返回null
     */
    User findByUsername(String username);

    /**
     * 根据id来修改用户密码
     * @param uid     用户id
     * @param password     用户的新密码
     * @param modifiedUser   修改者
     * @param modifiedTime    修改时间
     * @return   受影响行数
     */
    Integer updatePasswordByUidInteger(@Param("uid") Integer uid,
                                       @Param("password") String password,
                                       @Param("modifiedUser") String modifiedUser, @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据id查询用户数据
     * @param uid   用户的id
     * @return  如果存在返回user对象  不存在就返回null
     */
    User   findByUid(Integer uid);


    /**
     * 更新用户资料
     * @param user   用户数据
     * @return  受影响行数
     */
    Integer updateInfoByUid(User user);


    /**
     * param（sql映射文件中#{}占位符的变量名） 当sql文的占位符和映射接口
     * 的方法参数名不一致时，需要将某个参数强行注入到某个占位符变量上时，
     * 使用@param注解
     * 根据uid来修改用户头像
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateAvatarByUid( @Param("uid") Integer uid,
                                      @Param("avatar") String avatar,
                                      @Param("modifiedUser") String modifiedUser,
                                      @Param("modifiedTime")  Date modifiedTime);
}
