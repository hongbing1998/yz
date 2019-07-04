package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.edu.cdtu.yz.bean.Apply;
import org.edu.cdtu.yz.bean.Employment;
import org.edu.cdtu.yz.bean.School;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.IApplyService;
import org.edu.cdtu.yz.service.IEmploymentService;
import org.edu.cdtu.yz.service.ISchoolService;
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
@RequestMapping("/apply")
public class ApplyController {
    @Autowired
    public IApplyService applyService;
    @Autowired
    public ISchoolService schoolService;
    @Autowired
    public IEmploymentService employmentService;



    /**
     * 保存、修改 【区分id即可】
     * @param apply  传递的实体
     * @return Ajaxresult转换结果
     */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public String save(Apply apply, Model model) {
        try {
            if(apply.getId()!=null){

            }else{
                Employment employment = employmentService.selectById(apply.getEmployId());
                apply.setEmployTitle(employment.getTitle());
                apply.setApplyDate(DateUtil.getFormatCurrentDate());
                    applyService.insert(apply);
                model.addAttribute("flag", 1);
            }
            return "/main";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("flag", -1);
            return "/main";
        }
    }

    //删除对象信息
    @RequiresPermissions(value = {"apply"}, logical = Logical.OR)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id, Model model) {
        try {
            applyService.deleteById(id);
            List<Apply> employmentList = applyService.selectList(new EntityWrapper<Apply>()
                    .orderBy("applyDate", false)
            );
            model.addAttribute("allApply", employmentList);
            return "helpteacher/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "/error/unAuth";
        }
    }

    //获取用户
    @RequiresPermissions(value = {"apply"}, logical = Logical.OR)
    @RequestMapping(value = "/apply/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") String id, Model model)
    {
        model.addAttribute("Apply", applyService.selectById(id));
        return "/helpteacher/show";
    }

    //查看所有的报名人员信息
    @RequiresPermissions(value = {"apply"}, logical = Logical.OR)
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(String name, Model model) {
        List<Apply> employmentList = applyService.selectList(new EntityWrapper<Apply>()
                .like("name", name).orderBy("applyDate", false)
        );
        model.addAttribute("allApply", employmentList);
        return "/helpteacher/index";
    }

    //报名

    @RequestMapping(value = "/addlist", method = RequestMethod.GET)
    public String getList(Model model) {
        List<School> schools = schoolService.selectList(new EntityWrapper<School>()
                .orderBy("school_name")
        );
        List<Employment> employs = employmentService.selectList(new EntityWrapper<Employment>()
                .orderBy("title")
        );
        model.addAttribute("schools", schools);
        model.addAttribute("employs", employs);
        return "/helpteacher/add";
    }


    /**
    * 分页查询数据：
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequiresPermissions(value = {"apply"}, logical = Logical.OR)
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody PageQuery query) {
        Page<Apply> page = new Page<>(query.getPage(), query.getRows());
        page = applyService.selectPage(page);
        return AjaxResult.me().setResultObj(new PageList<>(page.getPages(), page.getRecords()));
    }
}
