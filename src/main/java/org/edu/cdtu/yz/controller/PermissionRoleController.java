package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.edu.cdtu.yz.bean.Permission;
import org.edu.cdtu.yz.bean.PermissionRole;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.IPermissionRoleService;
import org.edu.cdtu.yz.service.IPermissionService;
import org.edu.cdtu.yz.util.AjaxResult;
import org.edu.cdtu.yz.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@Controller
@RequestMapping("/permissionRole")
public class PermissionRoleController {
    @Autowired
    public IPermissionRoleService permissionRoleService;

    @Autowired
    public IPermissionService permissionService;
    /**
     * 保存、修改 【区分id即可】
     * @param permissionRole  传递的实体
     * @return Ajaxresult转换结果
     */


    //删除对象信息
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            permissionRoleService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public AjaxResult get(@PathVariable("id") Long id)
    {
        return AjaxResult.me().setResultObj(permissionRoleService.selectById(id));
    }


    //查看所有的员工信息
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public AjaxResult list() {
        return AjaxResult.me().setResultObj(permissionRoleService.selectList(null));
    }


    /**
    * 分页查询数据：
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody PageQuery query) {
        Page<PermissionRole> page = new Page<PermissionRole>(query.getPage(),query.getRows());
        page = permissionRoleService.selectPage(page);
        return AjaxResult.me().setResultObj(new PageList<PermissionRole>(page.getPages(), page.getRecords()));
    }

    @RequiresPermissions(value = {"role"}, logical = Logical.OR)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestParam(value = "id") String roleId, @RequestParam(value = "permissionIds[]", required = false) String[] permissionIds, Model model) {
        try {
            permissionRoleService.delete(new EntityWrapper<PermissionRole>().eq("roleId", roleId));
            for (String permissionId : permissionIds) {
                PermissionRole r = new PermissionRole();
                r.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
                r.setRoleId(roleId);
                r.setPermissionId(permissionId);
                Permission p = permissionService.selectOne(new EntityWrapper<Permission>().eq("id", permissionId));
                PermissionRole pr = permissionRoleService.selectOne(new EntityWrapper<PermissionRole>().eq("permissionId", p.getFatherPermission())
                        .eq("roleId", "roleId"));
                if (pr == null) {
                    PermissionRole lr = new PermissionRole();
                    lr.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
                    lr.setRoleId(roleId);
                    lr.setPermissionId(p.getFatherPermission());
                    permissionRoleService.insert(lr);
                }
                permissionRoleService.insert(r);
            }
            model.addAttribute("msg", "success");
            return "redirect:/role/list";
        } catch (Exception e) {
            e.printStackTrace();
            return "error/error";
        }
    }
}
