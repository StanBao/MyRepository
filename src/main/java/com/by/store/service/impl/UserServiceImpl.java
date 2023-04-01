package com.by.store.service.impl;

import com.by.store.entity.User;
import com.by.store.mapper.UserMapper;
import com.by.store.service.IUserService;
import com.by.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/*用户模块业务层的实现类*/
@Service     //将当前类交给spring管理
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        //通过user参数来获取传递过来的username
        String username = user.getUsername();
        //调用findByUsername(username)判断用户是否被注册过
        User result = userMapper.findByUsername(username);
        //result 如为null 说明没有重名可以注册  如不为null 说明重名 抛出异常
        if (result != null) {
            throw new UsernameDuplicatedException("用户名被占用");
        }

        //密码加密   md5算法     8776sfdfs-sdfxc234-32sdf-asx34-a1234
        //（串 + password + 串）   ------md5算法加密 ------ 连续加密3次
        //盐值 + pwd + 盐值 ======= 盐值就是一个随机的字符串
        String oldPassword = user.getPassword();
        //生成一个盐值  UUID  是java utli的一个api
        String salt = UUID.randomUUID().toString().toUpperCase();
        //补全数据   记录下盐值
        user.setSalt(salt);
        //将密码和salt作为整体进行加密
        String md5Password = getMD5Password(oldPassword, salt);
        //加密后的密码重新设置到user中
        user.setPassword(md5Password);


        //补全数据  is_delete 设置成0
        user.setIsDelete(0);

        //补全数据   4个日志信息
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        //执行注册插入  row==1 说明成功
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("注册中产生了未知异常");
        }

    }


    /*定义一个md5算法加密*/
    private String getMD5Password(String password, String salt) {
        //md5方法的调用
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        //返回加密后的密码
        return password;

    }



    @Override
    public User login(String username, String password) {
        //根据用户名查用户数据是否存在   如不在  抛异常
        User result = userMapper.findByUsername(username);
        if (result ==null){
            throw new UserNotFoundException("用户不存在");
        }
        //检测密码是否匹配
        //1.先获取数据库中的md5密码
        String oldpassword = result.getPassword();
        //2.和用户传来的密码比较
        //2.1 先获取上次注册时的盐值
        String salt = result.getSalt();
        //2.2将用户的密码按照相同算法加密
        String md5Password = getMD5Password(password, salt);
        //3 两个MD5密码进行比较
        if (!md5Password.equals(oldpassword)){
            throw new PasswordNotMatchException("密码错误");
        }
        // 判断isdelete  字段是否为1
        if (result.getIsDelete() ==1){
            throw new UserNotFoundException("用户名不存在");
        }

        return result;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据不存在");
        }
        //原始密码与新密码相比较
        String oldMd5Password = getMD5Password(oldPassword, result.getSalt());
        if (!result.getPassword().equals(oldMd5Password)){
            throw new PasswordNotMatchException("密码错误");
        }
        //将新密码设置到数据库 加密后更新
        String NewMd5Password = getMD5Password(newPassword, result.getSalt());
        Integer rows = userMapper.updatePasswordByUidInteger(uid, NewMd5Password, username, new Date());
        if (rows != 1){
            throw new UpdateException("未知错误");
        }
    }


    /**
     * 先用此方法把电话等信息存入user对象
     * @param uid    用户id
     * @return  user对象
     */
    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1){
            throw  new UserNotFoundException("用户数据不存在");
        }
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());

        return user;

    }

    /**
     * 再把uid 修改者等信息存入user对象  存入数据库
     * uid   username 可以从session获取
     * @param uid    用户id
     * @param username   用户名
     * @param user   用户对象数据
     */
    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1){
            throw  new UserNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        Integer rows = userMapper.updateInfoByUid(user);
        if (rows!= 1){
            throw new UpdateException("更新数据时产生未知异常");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1){
            throw  new UserNotFoundException("用户数据不存在");
        }
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if (rows!= 1){
            throw new UpdateException("更新数据时产生未知异常");
        }
    }
}
