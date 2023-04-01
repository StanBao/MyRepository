package com.by.store.mapper;

import com.by.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTest {
    @Autowired
    private DistrictMapper districtMapper;
    @Test
    public void findByParent(){
        List<District> list = districtMapper.findByParent("210100");
        for (District district : list) {
            System.out.println(district);
        }
    }

    @Test
    public void findNameByCode(){
        String nameByCode = districtMapper.findNameByCode("610000");
        System.out.println(nameByCode);
    }
}
