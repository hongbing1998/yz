package org.edu.cdtu.yz.service;

import org.edu.cdtu.yz.bean.Role;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wencheng
 * @since 2019-06-27
 */
public interface IRoleService extends IService<Role> {
   public List<Role> getRoles(String id);
}
