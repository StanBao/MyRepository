package com.by.store.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/*
* 作为实体类的基类 创建者创建时间 修改中修改时间  是很多张表里的字段   很多实体类需要他们
* */
@Data
public class BaseEntity  implements Serializable {
    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;
}
