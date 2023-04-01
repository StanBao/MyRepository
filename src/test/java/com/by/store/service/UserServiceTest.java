package com.by.store.service;

import com.by.store.entity.User;
import com.by.store.mapper.UserMapper;
import com.by.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//SpringBootTest 标注成测试类    不会随同项目一起打包
@SpringBootTest
//@RunWith 表示启动这个单元测试类   需要传参 SpringRunner.class
@RunWith(SpringRunner.class)
public class UserServiceTest {
    //userMapper 会爆红  因为idea有检测功能  接口不能直接创建bean
    @Autowired
    private IUserService iUserService;

    /**
     * 单元测试   不用启动项目  提升测试效率
     * 必须被@Test注解修饰    返回值必须void   必须public  参数不指定任何类型
     */
    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("by");
            user.setPassword("123456");
            iUserService.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());

        }
    }

    @Test
    public void login(){
        User baoyu = iUserService.login("by", "123456");
        System.out.println(baoyu);
    }

   @Test
    public void changePassword(){
        iUserService.changePassword(9,"by","123","123456");
   }

   @Test
    public void getByUid(){
       System.out.println(iUserService.getByUid(9));
   }
   @Test
    public void  changeInfo(){
        User user = new User();
        user.setPhone("18912341234");
        user.setEmail("asd@qq.com");
        user.setGender(0);
        iUserService.changeInfo(4,"管理员",user);
   }

   @Test
    public void changeAvatar(){
        iUserService.changeAvatar(4,"test.jpg","xiaoming");
   }
}
