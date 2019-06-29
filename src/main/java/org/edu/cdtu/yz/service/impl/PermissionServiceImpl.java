package org.edu.cdtu.yz.service.impl;

import org.edu.cdtu.yz.bean.Permission;
import org.edu.cdtu.yz.mapper.PermissionMapper;
import org.edu.cdtu.yz.service.IPermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wencheng
 * @since 2019-06-27
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {
@Autowired
PermissionMapper permissionMapper;
    @Override
    public List<Permission> getPermissions(String id) {
        return permissionMapper.selectPermissions(id);
    }
}
