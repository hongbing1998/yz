package org.edu.cdtu.yz.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.edu.cdtu.yz.bean.Demand;
import org.edu.cdtu.yz.mapper.DemandMapper;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.IDemandService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wencheng
 * @since 2019-06-27
 */
@Service
public class DemandServiceImpl extends ServiceImpl<DemandMapper, Demand> implements IDemandService {
    @Override
    public Page<Map<String, Object>> selectDemandsPage(PageQuery pageQuery) {
        Page<Map<String,Object>> page = new Page<>(pageQuery.getPage(), pageQuery.getRows());
        List<Map<String, Object>> maps = baseMapper.selectDemandsInfo(pageQuery);
        return page.setRecords(maps);
    }
}
