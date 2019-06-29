package org.edu.cdtu.yz.service.impl;

import org.edu.cdtu.yz.bean.Role;
import org.edu.cdtu.yz.mapper.RoleMapper;
import org.edu.cdtu.yz.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
@Autowired
RoleMapper roleMapper;
    @Override
    public List<Role> getRoles(String id) {
        System.out.println(id+"dwdwdw");
        return roleMapper.selectRoles(id);
    }
}
