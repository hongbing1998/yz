package org.edu.cdtu.yz.service;

import org.edu.cdtu.yz.bean.Permission;
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
public interface IPermissionService extends IService<Permission> {
    public List<Permission> getPermissions(String id);
}
