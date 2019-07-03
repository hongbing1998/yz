package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.edu.cdtu.yz.Relam.ShiroRealm;
import org.edu.cdtu.yz.bean.Path;
import org.edu.cdtu.yz.bean.Policy;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.IPathService;
import org.edu.cdtu.yz.util.AjaxResult;
import org.edu.cdtu.yz.util.DateUtil;
import org.edu.cdtu.yz.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Path path){
        try {
            if(path.getId()!=null){
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
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    //删除对象信息
    @ResponseBody
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") String id){
        try {
            pathService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value = "获取客服", notes = "根据cid获取客服")
    @ApiImplicitParam(name = "id", value = "路线id", required = true, dataType = "String")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public AjaxResult get(@PathVariable("id") String id)
    {
        return AjaxResult.me().setResultObj(pathService.selectById(id));
    }


    //查看所有的员工信息
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public AjaxResult list() {
        return AjaxResult.me().setResultObj(pathService.selectList(null));
    }


    /**
    * 分页查询数据：
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody PageQuery query) {
        Page<Path> page = new Page<Path>(query.getPage(),query.getRows());
        page = pathService.selectPage(page);
        return AjaxResult.me().setResultObj(new PageList<Path>(page.getPages(), page.getRecords()));
    }

    @RequestMapping(value = "/toindex",method = RequestMethod.GET)
    public String  toindex(Model model) {
        Page<Path> page = new Page<Path>(0,10);
        page = pathService.selectPage(page);
        model.addAttribute("resultObj",new PageList<Path>(page.getPages(), page.getCurrent(),page.getRecords()));
        return "Process/index";
    }
    //去修改页面
    @RequestMapping(value = "toedit/{id}",method = RequestMethod.GET)
    public String toedit(@PathVariable("id") String id,Model model)
    {
        model.addAttribute("path",pathService.selectById(id));
        return "Process/edit";
    }
}
