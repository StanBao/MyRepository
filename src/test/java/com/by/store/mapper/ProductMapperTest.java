package com.by.store.mapper;

import com.by.store.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperTest {
    @Autowired
    private ProductMapper productMapper;
    @Test
    public void findHotList(){
        List<Product> hotList = productMapper.findHotList();
        System.out.println(hotList);
    }

    @Test
    public void findById() {
        Integer id = 10000017;
        Product result = productMapper.findById(id);
        System.out.println(result);
    }
}
