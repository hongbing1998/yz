package org.edu.cdtu.yz;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.session.SqlSessionFactory;
import org.edu.cdtu.yz.bean.Apply;
import org.edu.cdtu.yz.bean.Employment;
import org.edu.cdtu.yz.bean.User;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class YzApplicationTests {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IMenuService iMenuService;
    @Autowired
    private IDemandService demandService;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    public IEmploymentService employmentService;

    @Autowired
    private IApplyService applyService;

    @Test
    public void DataSourceTest() throws SQLException {
        System.out.println("数据源：" + dataSource.getClass());
        System.out.println("数据库连接：" + dataSource.getConnection());
    }

    @Test
    public void MybatisTest() {
        System.out.println("sqlSessionFactory = " + sqlSessionFactory);
    }

    @Test
    public void MpTest() {
        User user = iUserService.selectById(1);
        System.out.println("user = " + user);
    }

    @Test
    public void DemandTest() {
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPage(1);// 设置当前页码（从1开始）
        pageQuery.setRows(1);// 设置每页大小
        Page<Map<String, Object>> page = demandService.selectDemandsPage(pageQuery);
        System.out.println("page = " + page);
        System.out.println("page.getRecords() = " + page.getRecords());
    }

    @Test
    public void MenuTest() {
        List<Employment> employmentList = employmentService.selectList(new EntityWrapper<Employment>()
                .like("title", "乡")
        );
        System.out.println(employmentList);
    }
}
