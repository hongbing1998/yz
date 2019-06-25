package org.edu.cdtu.yz;


import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class YzApplicationTests {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void DataSourceTest() throws SQLException {
        System.out.println("数据源：" + dataSource.getClass());
        System.out.println("数据库连接：" + dataSource.getConnection());
    }

    @Test
    public void MybatisTest() {
        System.out.println("sqlSessionFactory = " + sqlSessionFactory);
    }
}
