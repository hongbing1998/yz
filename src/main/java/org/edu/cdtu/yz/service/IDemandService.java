package org.edu.cdtu.yz.service;

import org.edu.cdtu.yz.bean.Demand;
import com.baomidou.mybatisplus.service.IService;
import org.edu.cdtu.yz.query.PageQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wencheng
 * @since 2019-06-27
 */
public interface IDemandService extends IService<Demand> {
    List<Map<String, Object>> selectDemandsInfo(PageQuery pageQuery);
}
