package org.edu.cdtu.yz.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.edu.cdtu.yz.bean.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wencheng
 * @since 2019-06-27
 */
public interface UserMapper extends BaseMapper<User> {

}
