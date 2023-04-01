package com.by.store.service;

import com.by.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTest {
    @Autowired
    private IAddressService addressService;

    @Test
    public void addNewAddress(){
        Address address = new Address();
        address.setPhone("123123123123");
        address.setName("先后顺序");
        addressService.addNewAddress(10,"管理员",address);
    }

    @Test
    public void setDefault(){
        addressService.setDefault(4,10,"Admin");
    }

    @Test
    public  void delete(){
        addressService.delete(4,10,"管理员");
    }
}
