package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.edu.cdtu.yz.bean.Permission;
import org.edu.cdtu.yz.bean.PermissionRole;
import org.edu.cdtu.yz.bean.Role;
import org.edu.cdtu.yz.bean.RoleUser;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.*;
import org.edu.cdtu.yz.util.AjaxResult;
import org.edu.cdtu.yz.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    public IRoleService roleService;
    @Autowired
    public IRoleUserService roleUserService;
    @Autowired
    public IUserService userService;
    @Autowired
    public IPermissionRoleService permissionRoleService;
    @Autowired
    public IPermissionService permissionService;
    /**
     * 保存、修改 【区分id即可】
     * @param role  传递的实体
     * @return Ajaxresult转换结果
     */
    @ResponseBody
    @RequiresPermissions(value = {"role"}, logical = Logical.OR)
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Role role) {
        try {
            System.out.println(role.getId());
            if (role.getId() != "") {
                System.out.println("ch");
                roleService.update(role, new EntityWrapper<Role>().eq("id", role.getId()));
            } else {
                System.out.println("vv");
                role.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                    roleService.insert(role);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false);
        }
    }

    @RequiresPermissions(value = {"role"}, logical = Logical.OR)
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String get(String id, Model model) {
        model.addAttribute("role", roleService.selectById(id));
        System.out.println(roleService.selectById(id).getId());
        return "Role/add";
    }
    //删除对象信息
    @RequiresPermissions(value = {"role"}, logical = Logical.OR)
    @ResponseBody
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") String id) {
        try {
            roleService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequiresPermissions(value = {"role"}, logical = Logical.OR)
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public AjaxResult get(@PathVariable("id") String id)
    {
        return AjaxResult.me().setResultObj(roleService.selectById(id));
    }


    //查看所有的员工信息
    @RequiresPermissions(value = {"role"}, logical = Logical.OR)
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("roles", roleService.selectList(null));
        return "Role/index";
    }

    @RequiresPermissions(value = {"role"}, logical = Logical.OR)
    @RequestMapping(value = "/getRolePerssion", method = RequestMethod.GET)
    public String getRolePerssion(String id, Model model) {
        System.out.println(id);
        List<PermissionRole> roleuser = permissionRoleService.selectList(new EntityWrapper<PermissionRole>().eq("roleId", id));
        List<Permission> roles = permissionService.selectList(new EntityWrapper<Permission>().ne("father_permission", "0"));
        for (Permission role : roles) {
            for (PermissionRole roleUser : roleuser) {
                if (role.getId().equals(roleUser.getPermissionId())) {
                    role.setChoice(true);

                }
            }
        }
        model.addAttribute("role", roleService.selectById(id));
        model.addAttribute("permissions", roles);
        return "Role/edit";
    }

    @RequiresPermissions(value = {"admin"}, logical = Logical.OR)
    @RequestMapping(value = "/getUserRoles", method = RequestMethod.GET)
    public String getUserRoles(String id, Model model) {
        List<RoleUser> roleuser = roleUserService.selectList(new EntityWrapper<RoleUser>().eq("user_id", id));
        List<Role> roles = roleService.selectList(null);
        for (Role role : roles) {
            for (RoleUser roleUser : roleuser) {
                if (role.getId().equals(roleUser.getRoleId())) {
                    role.setChoice(true);

                }
            }
        }
        Map<String, Object> roleusers = new HashMap<>();
        model.addAttribute("user", userService.selectById(id));
        model.addAttribute("roles", roles);
        return "User/edit";
    }
    /**
    * 分页查询数据：
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody PageQuery query) {
        Page<Role> page = new Page<Role>(query.getPage(),query.getRows());
        page = roleService.selectPage(page);
        return AjaxResult.me().setResultObj(new PageList<Role>(page.getPages(), page.getRecords()));
    }

}
