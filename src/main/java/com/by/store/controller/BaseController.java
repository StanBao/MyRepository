package com.by.store.controller;

import com.by.store.controller.ex.*;
import com.by.store.service.ex.*;
import com.by.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/** 控制层的基类 主要来处理异常*/
public class BaseController {
    /** 操作成功的状态码*/
    public static final int OK = 200;

    //请求处理方法  这个方法的返回值就是需要传递给前端的数据
    //自动将异常对象传递给此方法的参数列表上
    //当项目产生异常  被统一拦截到此方法中  此时就充当的是请求处理方法  返回值直接给前端
    @ExceptionHandler({ServiceException.class,FileUploadException.class})  //用于统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名已经被占用");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("注册时产生未知异常");
        } else if (e instanceof UserNotFoundException) {
            result.setState(5001);
            result.setMessage("用户数据不存在");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage("用户密码错误");
        } else if (e instanceof UpdateException) {
            result.setState(5003);
            result.setMessage("更新数据时产生未知异常");
        }else if (e instanceof FileEmptyException) {
            result.setState(6000);
            result.setMessage("文件不能为空");
        }else if (e instanceof FileSizeException) {
            result.setState(6001);
            result.setMessage("文件大小超出范围");
        }else if (e instanceof FileStateException) {
            result.setState(6002);
            result.setMessage("文件状态异常");
        }else if (e instanceof FileTypeException) {
            result.setState(6003);
            result.setMessage("不支持的文件类型");
        }else if (e instanceof FileUploadIOException) {
            result.setState(6004);
            result.setMessage("文件传输出现问题");
        }else if (e instanceof FileUploadException) {
            result.setState(6005);
            result.setMessage("文件上传异常");
        }else if (e instanceof AddressCountLimitException) {
            result.setState(6006);
            result.setMessage("地址数量超出上限");
        }else if (e instanceof AddressNotFoundException) {
            result.setState(6007);
            result.setMessage("地址不存在");
        } else if (e instanceof AccessDenieddException) {
            result.setState(6008);
            result.setMessage("非法数据访问");
        }else if (e instanceof DeleteException) {
            result.setState(6009);
            result.setMessage("删除出现异常");
        }else if (e instanceof ProductNotFoundException) {
            result.setState(7000);
            result.setMessage("商品不存在");
        }else if (e instanceof CartNotFoundException) {
            result.setState(4007);
        }

        return result;
    }

    /**
     * 获取session对象的uid
     * @param session
     * @return 当前登陆的用户uid的值
     */
   protected final Integer getUidFromSession(HttpSession session){
       return Integer.valueOf(session.getAttribute("uid").toString());
   }

    /**
     * 获取session对象 的用户名
     * @param session
     * @return  当前登陆的用户的用户名
     */
   protected final String getUsernameFromSession(HttpSession session){
         return  session.getAttribute("username").toString();
   }


}
