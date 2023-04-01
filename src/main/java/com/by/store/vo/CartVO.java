package com.by.store.vo;
import lombok.Data;

import java.io.Serializable;

/** 购物车数据的Value Object类
 * 因为是多表查询  product cart对象都不能接收返回结果
 * 所以需要创建一个值对象VO  来接多表查询数据
 * */
@Data
public class CartVO implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String title;
    private Long realPrice;
    private String image;

    // Generate: Getter and Setter、Generate hashCode() and equals()、toString()
}