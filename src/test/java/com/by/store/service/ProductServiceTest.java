package com.by.store.service;

import com.by.store.entity.Product;
import com.by.store.mapper.ProductMapper;
import com.by.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {
    @Autowired
    private IProductService productService;
    @Test
    public void findById() {
        try {
            Integer id = 10000001;
            Product result = productService.findById(id);
            System.out.println(result);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
