package com.by.store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class StoreApplicationTests {
    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }


    /*
       数据库连接池
       hikari 日本人发明的连接池  springboot默认连接池
        HikariProxyConnection@6633388 wrapping com.mysql.cj.jdbc.ConnectionImpl@3b218c74
        测试数据库是否连接成功
    * */
    @Test
    void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

}
