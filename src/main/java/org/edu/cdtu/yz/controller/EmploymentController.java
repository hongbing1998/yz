package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.edu.cdtu.yz.bean.Employment;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.IEmploymentService;
import org.edu.cdtu.yz.util.AjaxResult;
import org.edu.cdtu.yz.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/employment")
public class EmploymentController {
    @Autowired
    public IEmploymentService employmentService;

    /**
     * 保存、修改 【区分id即可】
     * @param employment  传递的实体
     * @return Ajaxresult转换结果
     */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Employment employment){
        try {
            if(employment.getId()!=null){
                    employmentService.updateById(employment);
            }else{
                    employmentService.insert(employment);
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
            employmentService.deleteById(id);
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
        return AjaxResult.me().setResultObj(employmentService.selectById(id));
    }


    //查看所有的员工信息
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public AjaxResult list() {
        return AjaxResult.me().setResultObj(employmentService.selectList(null));
    }


    /**
    * 分页查询数据：
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody PageQuery query) {
        Page<Employment> page = new Page<Employment>(query.getPage(),query.getRows());
        page = employmentService.selectPage(page);
        return AjaxResult.me().setResultObj(new PageList<Employment>(page.getPages(), page.getRecords()));
    }
}
