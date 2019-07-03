package org.edu.cdtu.yz;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.edu.cdtu.yz.bean.Demand;
import org.edu.cdtu.yz.bean.User;
import org.edu.cdtu.yz.mapper.DemandMapper;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.IDemandService;
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
    private IDemandService demandService;
    @Autowired
    private DataSource dataSource;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private IUserService iUserService;

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
    public void DemandMapperTest() {
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPage(1);
        pageQuery.setRows(1);
        List<Map<String, Object>> maps = demandService.selectDemandsInfo(pageQuery);
        System.out.println("maps = " + maps);
    }

    @Test
    public void EntityWrapperTest() {
        EntityWrapper<Map<String, Object>> entity = new EntityWrapper<>();

        //entity.lt()
    }
}
