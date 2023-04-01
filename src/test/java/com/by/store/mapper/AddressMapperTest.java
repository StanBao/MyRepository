package com.by.store.mapper;

import com.by.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTest {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void testInsert(){
        Address address = new Address();
        address.setUid(10);
        address.setName("孙悟空");
        address.setPhone("13945321234");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid(){
        Integer integer = addressMapper.countByUid(10);
        System.out.println(integer);
    }

    @Test
    public void findByUid(){
        List<Address> byUid = addressMapper.findByUid(10);
        System.out.println(byUid);
    }

    @Test
    public void findByAid(){
        Address byAid = addressMapper.findByAid(18);
        System.out.println(byAid);
    }

    @Test
    public void updateNonDefault(){
        Integer rows = addressMapper.updateNonDefault(10);
        System.out.println(rows);
    }

    @Test
    public void updateDefaultByAid(){
        Integer rows = addressMapper.updateDefaultByAid(16, "管理员", new Date());
        System.out.println(rows);
    }

    @Test
    public  void deleteByAid(){
        Integer rows = addressMapper.deleteByAid(7);
        System.out.println(rows);
    }
    @Test
    public void findLastModified(){
        Address lastModified = addressMapper.findLastModified(10);
        System.out.println(lastModified);
    }
}
