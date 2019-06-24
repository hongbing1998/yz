//package com.zhengqing.blog;
//
//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import User;
//import IUserService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@RunWith(SpringRunner.class)
////标识这是一个spring测试，指定入口【从哪儿加载spring配置文件】和应用类型,它就会扫描入口类子子孙孙包的bean
//@SpringBootTest(classes = BlogApplication.class)
//public class UserTest {
//
//    @Autowired
//    private IUserService userService;
//
//    @Test
//    public void testAdd() throws Exception{
//        User user = new User();
//        user.setId(10L);
//        user.setName("hello");
//        System.out.println(userService.insert(user));
//    }
//
//    @Test
//    public void testDelete() throws Exception{
//        EntityWrapper<User> wapper = new EntityWrapper<>();
//        userService.delete(wapper);
//    }
//
//    @Test
//    public void testSelect() throws Exception{
//        List<User> users = userService.selectList(null);
//        System.out.println(users);
//    }
//
//    @Test
//    public void testUpdate() throws Exception{
//        User user = new User();
////        user.setId(L);
//        user.setName("testsfs");
//        userService.insertOrUpdate(user);
//    }
//
//}
