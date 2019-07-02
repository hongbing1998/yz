package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.edu.cdtu.yz.bean.PermissionRole;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.IPermissionRoleService;
import org.edu.cdtu.yz.util.AjaxResult;
import org.edu.cdtu.yz.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
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
}
