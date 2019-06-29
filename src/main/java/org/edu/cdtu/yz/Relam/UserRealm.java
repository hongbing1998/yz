package org.edu.cdtu.yz.Relam;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
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
import java.util.Set;

import static com.alibaba.druid.sql.visitor.SQLEvalVisitorUtils.eq;

public class UserRealm extends AuthorizingRealm{
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("===执行认证===");

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        System.out.println(token.getUsername());
        User bean = userService.selectOne(new EntityWrapper<User>().eq("username",token .getUsername()));
        System.out.println(bean.getPassword());
        if(bean == null){
            throw new UnknownAccountException();
        }
        ByteSource credentialsSalt = ByteSource.Util.bytes(bean.getUsername());
        return new SimpleAuthenticationInfo(bean, bean.getPassword(),
             getName());

    }
    public static void main(String[] args){
Object r=new SimpleHash("MD5","232",null,1024);
        System.out.println(r);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("===执行授权===");
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        System.out.println(user.getId());
        if(user != null){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            // 角色与权限字符串集合
            Collection<String> rolesCollection = new HashSet<>();
            Collection<String> premissionCollection = new HashSet<>();
            System.out.println(user.getId());
            String id=user.getId();
            System.out.println(id);
            // 读取并赋值用户角色与权限
            List<Role> roles = roleService.getRoles(id);
            System.out.println(user.getId());
            for(Role role : roles){
                rolesCollection.add(role.getRoleName());
                System.out.println(role.getRoleName());
                List<Permission> permissions = permissionService.getPermissions(role.getId());
                for (Permission permission : permissions){
                    premissionCollection.add(permission.getPermissionUrl());
                }
                info.addStringPermissions(premissionCollection);
            }
            info.addRoles(rolesCollection);
            return info;
        }
        return null;
    }
}
