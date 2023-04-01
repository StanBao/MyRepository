package com.by.store.service;

import com.by.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictServiceTest {
    @Autowired
    private IDistrictService districtService;
    @Test
    public void getByParent(){
        //86表示中国  所有省的父代号都是86
        List<District> list = districtService.getByParent("86");
        for (District district : list) {
            System.out.println(district);
        }

    }
}
