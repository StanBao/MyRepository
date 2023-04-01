package com.by.store.mapper;

import com.by.store.entity.District;

import java.util.List;

public interface DistrictMapper {
    /**
     * 根据父代号查询区域信息
     * @param parent 父代号
     * @return 父区域下的所有区域的集合
     */
   List<District> findByParent(String parent);

    /**
     *
     * @param code
     * @return
     */
   String findNameByCode(String code);
}
