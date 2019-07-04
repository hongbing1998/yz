package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.edu.cdtu.yz.bean.RoleUser;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.IRoleUserService;
import org.edu.cdtu.yz.util.AjaxResult;
import org.edu.cdtu.yz.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@Controller
@RequestMapping("/roleUser")
public class RoleUserController {
    @Autowired
    public IRoleUserService roleUserService;

    /**
     * 保存、修改 【区分id即可】
     * @param roleUser  传递的实体
     * @return Ajaxresult转换结果
     */
    @RequiresPermissions(value = {"admin"}, logical = Logical.OR)
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public String save(@RequestParam(value = "id") String userId, @RequestParam(value = "roleIds[]", required = false) String[] roleIds, Model model) {
        try {
            roleUserService.delete(new EntityWrapper<RoleUser>().eq("user_id", userId));
            for (String roleId : roleIds) {
                RoleUser r = new RoleUser();
                r.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
                r.setRoleId(roleId);
                r.setUserId(userId);
                roleUserService.insert(r);
            }
            model.addAttribute("msg", "success");
            return "redirect:/user/list";
        } catch (Exception e) {
            e.printStackTrace();
            return "error/error";
        }
    }

    //删除对象信息

    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            roleUserService.deleteById(id);
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
        return AjaxResult.me().setResultObj(roleUserService.selectById(id));
    }


    //查看所有的员工信息

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public AjaxResult list() {
        return AjaxResult.me().setResultObj(roleUserService.selectList(null));
    }


    /**
    * 分页查询数据：
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody PageQuery query) {
        Page<RoleUser> page = new Page<RoleUser>(query.getPage(),query.getRows());
        page = roleUserService.selectPage(page);
        return AjaxResult.me().setResultObj(new PageList<RoleUser>(page.getPages(), page.getRecords()));
    }

}
