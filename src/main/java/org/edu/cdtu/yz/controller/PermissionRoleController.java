package org.edu.cdtu.yz.controller;

import org.edu.cdtu.yz.service.IPermissionRoleService;
import org.edu.cdtu.yz.bean.PermissionRole;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.util.AjaxResult;
import org.edu.cdtu.yz.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/permissionRole")
public class PermissionRoleController {
    @Autowired
    public IPermissionRoleService permissionRoleService;

    /**
     * 保存、修改 【区分id即可】
     * @param permissionRole  传递的实体
     * @return Ajaxresult转换结果
     */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody PermissionRole permissionRole){
        try {
            if(permissionRole.getId()!=null){
                    permissionRoleService.updateById(permissionRole);
            }else{
                    permissionRoleService.insert(permissionRole);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

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
    public PermissionRole get(@PathVariable("id")Long id)
    {
        return permissionRoleService.selectById(id);
    }


    //查看所有的员工信息
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<PermissionRole> list(){
        return permissionRoleService.selectList(null);
    }


    /**
    * 分页查询数据：
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<PermissionRole> json(@RequestBody PageQuery query) {
        Page<PermissionRole> page = new Page<PermissionRole>(query.getPage(),query.getRows());
        page = permissionRoleService.selectPage(page);
        return new PageList<PermissionRole>(page.getTotal(),page.getRecords());
    }
}
