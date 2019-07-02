package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.edu.cdtu.yz.bean.Apply;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.IApplyService;
import org.edu.cdtu.yz.util.AjaxResult;
import org.edu.cdtu.yz.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/apply")
public class ApplyController {
    @Autowired
    public IApplyService applyService;

    /**
     * 保存、修改 【区分id即可】
     * @param apply  传递的实体
     * @return Ajaxresult转换结果
     */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Apply apply){
        try {
            if(apply.getId()!=null){
                    applyService.updateById(apply);
            }else{
                    applyService.insert(apply);
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
            applyService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Apply get(@PathVariable("id")Long id)
    {
        return applyService.selectById(id);
    }


    //查看所有的员工信息
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Apply> list(){
        return applyService.selectList(null);
    }


    /**
    * 分页查询数据：
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Apply> json(@RequestBody PageQuery query) {
        Page<Apply> page = new Page<Apply>(query.getPage(),query.getRows());
        page = applyService.selectPage(page);
        return new PageList<Apply>(page.getPages(), page.getRecords());
    }
}
