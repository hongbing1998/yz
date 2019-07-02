package org.edu.cdtu.yz.Relam;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.edu.cdtu.yz.bean.Permission;
import org.edu.cdtu.yz.bean.Role;
import org.edu.cdtu.yz.bean.User;
import org.edu.cdtu.yz.service.IPermissionService;
import org.edu.cdtu.yz.service.IRoleService;
import org.edu.cdtu.yz.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * 自定义ShiroRealm
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;

    /**
     * 认证方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("===执行认证===");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        System.out.println("token.getUsername() = " + token.getUsername());
        User user = userService.selectOne(new EntityWrapper<User>().eq("username", token.getUsername()));
        if (user == null) {
            throw new UnknownAccountException();
        }
//        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());

    }

    /**
     * 授权方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("===进入授权===");
        User user = getCurrentUser();
        if (user != null) {
            // 已登录，执行授权
            System.out.println("===已登录，执行授权===");
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();// 授权信息
            Collection<String> rolesCollection = new HashSet<>();// 角色字符串集合
            Collection<String> permissionCollection = new HashSet<>();// 权限字符串集合
            List<Role> roles = roleService.getRoles(user.getId());// 获取该用户拥有的所有角色
            for (Role role : roles) {
                rolesCollection.add(role.getRoleName());
                System.out.println("role.getRoleName() = " + role.getRoleName());
                List<Permission> permissions = permissionService.getPermissions(role.getId());// 获取该角色拥有的所有权限
                for (Permission permission : permissions) {

                    permissionCollection.add(permission.getPerms());
                }
            }
            info.addStringPermissions(permissionCollection);// 增加权限信息
            info.addRoles(rolesCollection);// 增加角色信息
            return info;
        }
        return null;
    }

    /**
     * 获取当前用户信息
     */
    public static User getCurrentUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    public static void main(String[] args) {
        Object r = new SimpleHash("MD5", "232", null, 1024);
        System.out.println(r);
    }
}
