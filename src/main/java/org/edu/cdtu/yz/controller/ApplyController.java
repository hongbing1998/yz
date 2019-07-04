package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.edu.cdtu.yz.bean.Apply;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.IApplyService;
import org.edu.cdtu.yz.util.AjaxResult;
import org.edu.cdtu.yz.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
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
    public String save(@RequestBody Apply apply, Model model) {
        try {
            if(apply.getId()!=null){
                    applyService.updateById(apply);
            }else{
                    applyService.insert(apply);
            }
            model.addAttribute("allApply", applyService.selectList(null));
            return "helpteacher/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "/error/unAuth";
        }
    }

    //删除对象信息
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id, Model model) {
        try {
            applyService.deleteById(id);
            model.addAttribute("allApply", applyService.selectList(null));
            return "helpteacher/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "/error/unAuth";
        }
    }

    //获取用户
    @RequestMapping(value = "/apply/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") String id, Model model)
    {
        model.addAttribute("Apply", applyService.selectById(id));
        return "/helpteacher/show";
    }

    //查看所有的员工信息
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(String name, Model model) {
        List<Apply> employmentList = applyService.selectList(new EntityWrapper<Apply>()
                .like("name", name)
        );
        model.addAttribute("allApply", employmentList);
        return "/helpteacher/index";
    }


    /**
    * 分页查询数据：
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody PageQuery query) {
        Page<Apply> page = new Page<>(query.getPage(), query.getRows());
        page = applyService.selectPage(page);
        return AjaxResult.me().setResultObj(new PageList<>(page.getPages(), page.getRecords()));
    }
}
