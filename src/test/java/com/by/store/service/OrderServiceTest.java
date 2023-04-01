package com.by.store.service;
import com.by.store.service.IOrderService;
import com.by.store.entity.Order;
import com.by.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTest {
    @Autowired
    private IOrderService orderService;

    @Test
    public void create() {
        try {
            Integer aid = 16;
            Integer[] cids = {4, 5, 3};
            Integer uid = 10;
            String username = "订单管理员";
            Order order = orderService.create(aid, cids, uid, username);
            System.out.println(order);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
