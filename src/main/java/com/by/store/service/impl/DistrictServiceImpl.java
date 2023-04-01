package com.by.store.service.impl;

import com.by.store.entity.District;
import com.by.store.mapper.DistrictMapper;
import com.by.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired
    private DistrictMapper districtMapper;
    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);
        //在进行网络数据的传输时  为了避免无效数据的传输 可以将无效数据设置为null
        //节省流量  提高效率
        for (District district : list) {
            district.setId(null);
            district.setParent(null);
        }
        return list;
    }

    @Override
    public String getNameByCode(String code) {

        return districtMapper.findNameByCode(code);
    }
}
