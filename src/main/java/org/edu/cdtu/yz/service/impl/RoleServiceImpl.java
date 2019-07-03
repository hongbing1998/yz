package org.edu.cdtu.yz.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.edu.cdtu.yz.bean.Role;
import org.edu.cdtu.yz.mapper.RoleMapper;
import org.edu.cdtu.yz.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wencheng
 * @since 2019-06-27
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Override
    public List<Role> getRoles(String id) {
        System.out.println(id + "dwdwdw");
        return baseMapper.selectRoles(id);
    }
}
