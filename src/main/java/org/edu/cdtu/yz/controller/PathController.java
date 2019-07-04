package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.edu.cdtu.yz.Relam.ShiroRealm;
import org.edu.cdtu.yz.bean.Path;
import org.edu.cdtu.yz.bean.Path;
import org.edu.cdtu.yz.bean.Path;
import org.edu.cdtu.yz.bean.Path;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.IPathService;
import org.edu.cdtu.yz.util.AjaxResult;
import org.edu.cdtu.yz.util.DateUtil;
import org.edu.cdtu.yz.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/path")
public class PathController {
    @Autowired
    public IPathService pathService;

    /**
     * 保存、修改 【区分id即可】
     * @param path  传递的实体
     * @return Ajaxresult转换结果
     */
    @ResponseBody
    @RequiresPermissions(value = {"path"}, logical = Logical.OR)
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody  Path path) {
        try {
            if(path.getId()!=null){
                path.setCreateDate(DateUtil.getFormatCurrentDate());
                pathService.updateById(path);
            }else{
                path.setCreateDate(DateUtil.getFormatCurrentDate());
                path.setUserId(ShiroRealm.getCurrentUser().getId());
                path.setUserName(ShiroRealm.getCurrentUser().getUsername());
                pathService.insert(path);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //删除对象信息
    @RequiresPermissions(value = {"path"}, logical = Logical.OR)
    @ResponseBody
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") String id) {
        try {
            pathService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequiresPermissions(value = {"path"}, logical = Logical.OR)
    @ApiOperation(value = "获取客服", notes = "根据cid获取客服")
    @ApiImplicitParam(name = "id", value = "路线id", required = true, dataType = "String")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public AjaxResult get(@PathVariable("id") String id)
    {
        return AjaxResult.me().setResultObj(pathService.selectById(id));
    }


    //查看所有的员工信息
    @RequiresPermissions(value = {"path"}, logical = Logical.OR)
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public AjaxResult list() {
        return AjaxResult.me().setResultObj(pathService.selectList(null));
    }


    /**
    * 分页查询数据：
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequiresPermissions(value = {"path"}, logical = Logical.OR)
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody PageQuery query) {
        Page<Path> page = new Page<Path>(query.getPage(),query.getRows());
        page = pathService.selectPage(page);
        return AjaxResult.me().setResultObj(new PageList<Path>(page.getPages(), page.getRecords()));
    }

    @RequiresPermissions(value = {"path"}, logical = Logical.OR)
    @RequestMapping(value = "//page_query/{current}/{size}", method = RequestMethod.GET)
    public String toIndex(Model model, @PathVariable("current") int current, @PathVariable("size") int size) {
        Page<Path> page = new Page<>(current, size);
        EntityWrapper<Path> ew = new EntityWrapper<>();
        ew.orderBy("create_date", false);
        page = pathService.selectPage(page, ew);
        model.addAttribute("page", page);
        return "Process/index";
    }

    //去修改页面
    @RequiresPermissions(value = {"work"}, logical = Logical.OR)
    @RequestMapping(value = "toedit/{id}", method = RequestMethod.GET)
    public String toedit(@PathVariable("id") String id, Model model) {
        model.addAttribute("path", pathService.selectById(id));
        return "Process/edit";
    }

    //去查询据结果页面
    @RequiresPermissions(value = {"path"}, logical = Logical.OR)
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String toQueryBytitle(String title, Model model) {
        EntityWrapper<Path> ew = new EntityWrapper<>();
        ew.like("title", title);
        ew.orderBy("create_date", false);
        Page page = new Page(1, 5);
        pathService.selectPage(page, ew);
        model.addAttribute("page", page);
        return "Process/index";
    }
}
