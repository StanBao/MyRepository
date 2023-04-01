package com.by.store.controller;

import com.by.store.controller.ex.*;
import com.by.store.entity.User;
import com.by.store.service.IUserService;
import com.by.store.service.ex.InsertException;
import com.by.store.service.ex.UsernameDuplicatedException;
import com.by.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController  //controller和responsebody的结合体    ResponseBody表示此方法响应结果以json格式进行数据响应到前端
@RequestMapping("/users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

  /*  @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        //创建结果集对象
        JsonResult<Void> result = new JsonResult<>();

        try {
            userService.reg(user);
            result.setState(200);
            result.setMessage("用户注册成功");
        } catch (UsernameDuplicatedException e) {
            result.setState(4000);
            result.setMessage("用户名被占用");
        }catch (InsertException e){
            result.setState(5000);
            result.setMessage("注册时发生未知异常");
        }
        return result;

    }*/
    /**
     * 接收数据类型： 请求处理方法的参数列表设置为pojo来接收前端数据
     *  springMVC把前端url地址里的参数和pojo类的属性相对比
     *  一致的话  将值注入pojo类对应的属性上
     * */
    @RequestMapping("/reg")
    public JsonResult<Void> reg(User user){
      userService.reg(user);
      return new JsonResult<>(OK);
    }

    /**
     * 接收数据类型： 请求处理方法的参数列表设置为非pojo，例如string等
     * mvc会把请求的参数名和方法的参数名相比较
     * 一致的话  完成值的注入
     * */
    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data = userService.login(username, password);
        //服务器本身启动时就会创建一个全局的session对象
        //springboot直接使用session对象  将httpsession作为方法的形参
        //向session对象中完成数据绑定
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());


        return new JsonResult<User>(OK,data);
    }

    @RequestMapping("/change_password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return   new JsonResult<>(OK);
    }
    @RequestMapping("/get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
        User data = userService.getByUid(getUidFromSession(session));
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("/change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session){
       //user里面有四部分数据  username phone Email gender
       //uid需要再次封装进user
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid,username,user);
        return new JsonResult<>(OK);
    }

    //定义一个常量  图片的最大值10m
    public static final int AVATAR_MAX_SIZE=10*1024*1024;
    //定义一个上传文件的类型  的集合
    public static final List<String> AVATAR_TYPE = new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/jpg");
    }
    /**
     * MultipartFile是mvc提供的接口 为我们包装了获取文件类型的数据
     * 任何类型的file都可以接收 boot整合了mvc 只要方法形参上声明一个multiparty即可
     * @RequsetParam表示请求中的参数 如果名称不一致可以使用此注解进行标记和映射
     * @param session
     * @param file
     * @return
     */
    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session,
                                    @RequestParam("file") MultipartFile file){
       if (file.isEmpty()){
           throw new FileEmptyException("文件为空");
       }
       if (file.getSize()>AVATAR_MAX_SIZE){
           throw new FileSizeException("文件太大了");
       }
        String contentType = file.getContentType();
       if(!AVATAR_TYPE.contains(contentType)){
           throw new FileTypeException("文件类型不支持");
       }
       //上传的文件 .../upload/wenjian.png
        String parent = session.getServletContext().getRealPath("upload");
       //File对象指向这个路径，file是否存在
        File dir = new File(parent);
        if (!dir.exists()){
            dir.mkdirs();  //如果不存在 创建当前目录
        }
        //获取此文件名称  UUID来生成一个新字符串作为文件名
        //例如 vaw01.png
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");//找到.png的.所在的索引位置
        String suffix = originalFilename.substring(index);//截取.后面的部分 即.png
        //AS435R-DFSDF234-SDF234-2E-WEREWR.png
        String filename = UUID.randomUUID().toString().toUpperCase()+suffix;
        //把file里的数据写入新的空文件中
        File dest = new File(dir,filename); //这是一个空文件  文件名是我们UUID生成的
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        }catch (FileStateException e){
            throw new FileStateException("文件状态异常");
        }
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        //返回头像的路径/upload/test.png
        String avatar = "/upload/" + filename;
        userService.changeAvatar(uid,avatar,username);
        //返回头像路径给前端  用于头像展示用
        return new JsonResult<>(OK,avatar);
    }
}
