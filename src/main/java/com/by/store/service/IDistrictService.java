package com.by.store.service;

import com.by.store.entity.District;

import java.util.List;

public interface IDistrictService {
    /**
     *根据父代号来查询区域信息{省市区}
     * @param parent 父代号
     * @return 区域信息的集合
     */
    List<District> getByParent(String parent);

    /**
     * 根据code获取name
     * @param code 区域代号
     * @return 省市信息
     */
    String getNameByCode(String code);
}
