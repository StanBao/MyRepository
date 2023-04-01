package com.by.store.mapper;

import com.by.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

//SpringBootTest 标注成测试类    不会随同项目一起打包
@SpringBootTest
//@RunWith 表示启动这个单元测试类   需要传参 SpringRunner.class
@RunWith(SpringRunner.class)
public class UserMapperTest {
    //userMapper 会爆红  因为idea有检测功能  接口不能直接创建bean
    @Autowired
    private UserMapper userMapper;
    /**
     * 单元测试   不用启动项目  提升测试效率
     * 必须被@Test注解修饰    返回值必须void   必须public  参数不指定任何类型
     */
    @Test
    public void insert(){
        User user = new User();
        user.setUsername("baoyu");
        user.setPassword("123456");
        Integer result = userMapper.insert(user);
        System.out.println(result);
    }

    @Test
    public void findByUsername(){
        User user = userMapper.findByUsername("baoyu");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUidInteger(){
          userMapper.updatePasswordByUidInteger(3,"123","管理员",new Date());
    }

       @Test
    public void findByUid(){
           System.out.println(userMapper.findByUid(200));
       }

       @Test
    public  void updateInfoByUid(){
         User user = new User();
         user.setUid(9);
         user.setPhone("13604517894");
         user.setEmail("asdqwe@qq.com");
         user.setGender(1);
         user.setModifiedUser("测试员");
         user.setModifiedTime(new Date());
         userMapper.updateInfoByUid(user);
       }

       @Test
    public void updateAvatarByUid(){
        userMapper.updateAvatarByUid(4,"/aaa.png","admin",new Date());
       }

}
