package com.by.store.controller;

import com.by.store.entity.Address;
import com.by.store.service.IAddressService;
import com.by.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;
    @RequestMapping("/add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(uid,username,address);
        return new JsonResult<>(OK);
    }

    @RequestMapping({"","/"})
    public JsonResult<List<Address>> getByUid(HttpSession session){
        Integer uid = getUidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return new JsonResult<>(OK,data);

    }
    //restful风格  {aid}  如果{}里的参数和方法形参不一致 那就用pathvariable来强行注入 这里一致  可以省略
    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid")Integer aid,HttpSession session){
        addressService.setDefault(aid,getUidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
    @RequestMapping("{del}/delete")
    public JsonResult<Void> delete( @PathVariable("del") Integer aid,HttpSession session){
        addressService.delete(aid,getUidFromSession(session),getUsernameFromSession(session));
        return  new  JsonResult<>(OK);
    };
}
