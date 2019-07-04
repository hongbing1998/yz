package org.edu.cdtu.yz.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.edu.cdtu.yz.bean.Demand;
import org.edu.cdtu.yz.query.PageQuery;

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
    Page<Map<String, Object>> selectDemandsPage(PageQuery page);
}
