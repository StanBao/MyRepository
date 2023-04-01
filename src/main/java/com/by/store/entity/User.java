package com.by.store.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class User extends BaseEntity implements Serializable {
       private Integer uid;
       private String  username;
    private String password;
    private String salt;
    private String phone;
    private String email;
    private Integer gender;
    private String avatar;
    private Integer isDelete;

    //get和set    equals和hashcode   toString 实体类必备三要素
    //springboot 约定大于配置

}
