package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.edu.cdtu.yz.bean.School;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.ISchoolService;
import org.edu.cdtu.yz.util.AjaxResult;
import org.edu.cdtu.yz.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    public ISchoolService schoolService;

    /**
     * 保存、修改 【区分id即可】
     * @param school  传递的实体
     * @return Ajaxresult转换结果
     */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody School school){
        try {
            if(school.getId()!=null){
                    schoolService.updateById(school);
            }else{
                    schoolService.insert(school);
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
            schoolService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public School get(@PathVariable("id")Long id)
    {
        return schoolService.selectById(id);
    }


    //查看所有的员工信息
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<School> list(){
        return schoolService.selectList(null);
    }


    /**
    * 分页查询数据：
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<School> json(@RequestBody PageQuery query) {
        Page<School> page = new Page<School>(query.getPage(),query.getRows());
        page = schoolService.selectPage(page);
        return new PageList<School>(page.getPages(), page.getRecords());
    }
}
