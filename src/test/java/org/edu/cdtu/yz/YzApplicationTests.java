package org.edu.cdtu.yz;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.session.SqlSessionFactory;
import org.edu.cdtu.yz.bean.School;
import org.edu.cdtu.yz.bean.User;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.IDemandService;
import org.edu.cdtu.yz.service.IMenuService;
import org.edu.cdtu.yz.service.ISchoolService;
import org.edu.cdtu.yz.service.IUserService;
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
    public ISchoolService schoolService;
    @Autowired
    private IDemandService demandService;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void DataSourceTest() throws SQLException {
        System.out.println("数据源：" + dataSource.getClass());
        System.out.println("数据库连接：" + dataSource.getConnection());
        System.out.println("sqlSessionFactory = " + sqlSessionFactory);
    }

    @Test
    public void MybatisPlusTest() {
        User user = iUserService.selectById(1);
        System.out.println("user = " + user);
    }

    @Test
    public void DemandTest() {
        PageQuery query = new PageQuery(1, 1);
        Page<Map<String, Object>> page = demandService.selectDemandsPage(query);
        System.out.println("page = " + page);
        System.out.println("page.getRecords() = " + page.getRecords());
    }

    @Test
    public void MenuTest() {
        List<Map<String, Object>> map = iMenuService.getMenu("11");
        System.out.println(map);
    }

    @Test
    public void SchoolTest() {
        EntityWrapper<School> entityWrapper = new EntityWrapper<>();
        entityWrapper.setSqlSelect("id", "school_name", "school_info", "school_need_help");
        List<School> schools = schoolService.selectList(entityWrapper);
        for (School school : schools) {
            System.out.println("school = " + school);
        }
    }
}
