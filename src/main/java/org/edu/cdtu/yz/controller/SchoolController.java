package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.edu.cdtu.yz.Relam.ShiroRealm;
import org.edu.cdtu.yz.bean.School;
import org.edu.cdtu.yz.service.ISchoolService;
import org.edu.cdtu.yz.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin
@Controller
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private ISchoolService schoolService;

    /**
     * 跳转至编辑学校页面，将本条学校信息带过去
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") String id) {
        model.addAttribute("school", schoolService.selectById(id));
        return "School/edit";
    }

    /**
     * 跳转至显示学校页面，将本条学校信息带过去
     */
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(Model model, @PathVariable("id") String id) {
        model.addAttribute("school", schoolService.selectById(id));
        return "School/show";
    }

    /**
     * 根据id删除数据
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id) {
        try {
            schoolService.deleteById(id);
            return "redirect:../page_query/1/5";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 保存数据，数据有id则根据id更新，无id则新增数据
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(School school) {
        try {
            school.setSchoolOperateId(ShiroRealm.getCurrentUser().getId());
            school.setSchoolOperateName(ShiroRealm.getCurrentUser().getUsername());
            school.setSchoolOperateTime(DateUtil.getFormatCurrentDate());
            if (school.getId() != null) {
                schoolService.updateById(school);
            } else {
                schoolService.insert(school);
            }
            return "redirect:page_query/1/5";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 条件分页查询，跳转至学校管理主页
     */
    @RequestMapping(value = "/condition_query", method = RequestMethod.POST)
    public String conditionQuery(Model model, School school) {
        EntityWrapper<School> ew = new EntityWrapper<>();
        ew.like("school_name", school.getSchoolName());
        Page page = new Page(1, 5);
        schoolService.selectPage(page, ew);
        model.addAttribute("page", page);
        model.addAttribute("schoolName", school.getSchoolName());
        return "School/index";
    }

    /**
     * 分页查询数据
     */
    @RequestMapping(value = "/page_query/{current}/{size}", method = RequestMethod.GET)
    public String pageQuery(Model model, @PathVariable("current") int current, @PathVariable("size") int size) {
        Page<School> page = new Page<>(current, size);
        EntityWrapper<School> ew = new EntityWrapper<>();
        ew.orderBy("school_name", true);
        page = schoolService.selectPage(page, ew);
        model.addAttribute("page", page);
        return "School/index";
    }
}
