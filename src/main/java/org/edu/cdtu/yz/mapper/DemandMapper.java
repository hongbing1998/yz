package org.edu.cdtu.yz.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.edu.cdtu.yz.bean.Demand;
import org.edu.cdtu.yz.query.PageQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wencheng
 * @since 2019-06-27
 */
public interface DemandMapper extends BaseMapper<Demand> {
    List<Map<String, Object>> selectDemandsInfo(@Param("query") PageQuery query);
}
