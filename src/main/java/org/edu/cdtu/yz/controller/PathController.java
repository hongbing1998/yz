package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.edu.cdtu.yz.bean.Path;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.IPathService;
import org.edu.cdtu.yz.util.AjaxResult;
import org.edu.cdtu.yz.util.DateUtil;
import org.edu.cdtu.yz.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
                    pathService.insert(path);
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
    public Path get(@PathVariable("id")Long id)
    {
        return pathService.selectById(id);
    }


    //查看所有的员工信息
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Path> list(){
        return pathService.selectList(null);
    }


    /**
    * 分页查询数据：
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Path> json(@RequestBody PageQuery query) {
        Page<Path> page = new Page<Path>(query.getPage(),query.getRows());
        page = pathService.selectPage(page);
        return new PageList<Path>(page.getPages(), page.getRecords());
    }
}
