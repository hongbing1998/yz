//package org.edu.cdtu.yz;
//
//
//import com.baomidou.mybatisplus.plugins.Page;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.edu.cdtu.yz.bean.User;
//import org.edu.cdtu.yz.service.IUserService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class YzApplicationTests {
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private SqlSessionFactory sqlSessionFactory;
//
//    @Autowired
//    private IUserService iUserService;
//
//    @Test
//    public void DataSourceTest() throws SQLException {
//        System.out.println("数据源：" + dataSource.getClass());
//        System.out.println("数据库连接：" + dataSource.getConnection());
//    }
//
//    @Test
//    public void MybatisTest() {
//        System.out.println("sqlSessionFactory = " + sqlSessionFactory);
//    }
//
//    @Test
//    public void MpTest() {
//        User user = iUserService.selectById(1);
//        System.out.println("user = " + user);
//        Page<User> page = new Page<>(1, 1);
//
//        Page<User> userPage = iUserService.selectPage(page, null);
//        System.out.println("userPage = " + userPage);
//        System.out.println("userPage = " + userPage.getRecords());
//    }
//}
