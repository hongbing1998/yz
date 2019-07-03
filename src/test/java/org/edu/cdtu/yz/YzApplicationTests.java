package org.edu.cdtu.yz;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.session.SqlSessionFactory;
import org.edu.cdtu.yz.bean.User;
import org.edu.cdtu.yz.bean.Work;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.IDemandService;
import org.edu.cdtu.yz.service.IMenuService;
import org.edu.cdtu.yz.service.IUserService;
import org.edu.cdtu.yz.service.IWorkService;
import org.edu.cdtu.yz.util.AjaxResult;
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
    private IWorkService workService;
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
        List<Work> works = workService.selectList(new EntityWrapper<Work>()
                .like("title", "0")
        );
        AjaxResult ajaxResult = AjaxResult.me().setResultObj(works);
        Object resultObj = ajaxResult.getResultObj();
        System.out.println(resultObj.toString());
    }
}
