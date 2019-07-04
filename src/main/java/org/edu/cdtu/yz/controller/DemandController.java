package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.edu.cdtu.yz.Relam.ShiroRealm;
import org.edu.cdtu.yz.bean.Demand;
import org.edu.cdtu.yz.bean.School;
import org.edu.cdtu.yz.service.IDemandService;
import org.edu.cdtu.yz.service.ISchoolService;
import org.edu.cdtu.yz.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@CrossOrigin
@RequestMapping("/demand")
public class DemandController {
    @Autowired
    public IDemandService demandService;
    @Autowired
    public ISchoolService schoolService;

    /**
     * 跳转至添加需求页面，将学校信息带过去
     */
    @RequiresPermissions(value = {"demand"}, logical = Logical.OR)
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("schools", schoolService.selectList(null));
        return "Need/add";
    }

    /**
     * 跳转至编辑需求页面，将本条需求信息带过去
     */
    @RequiresPermissions(value = {"demand"}, logical = Logical.OR)
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") String id) {
        model.addAttribute("demand", demandService.selectById(id));
        model.addAttribute("schools", schoolService.selectList(null));
        return "Need/edit";
    }

    /**
     * 跳转至编辑需求页面，将需求信息带过去
     */
    @RequiresPermissions(value = {"demand"}, logical = Logical.OR)
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(Model model, @PathVariable("id") String id) {
        model.addAttribute("demand", demandService.selectById(id));
        return "Need/show";
    }

    /**
     * 根据id删除数据
     */
    @RequiresPermissions(value = {"demand"}, logical = Logical.OR)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id) {
        try {
            demandService.deleteById(id);
            return "redirect:../page_query/1/5";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 保存数据，数据有id则根据id更新，无id则新增数据
     */
    @RequiresPermissions(value = {"demand"}, logical = Logical.OR)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Demand demand) {
        try {
            School school = schoolService.selectById(demand.getSchoolId());
            demand.setSchoolName(school.getSchoolName());
            demand.setUserId(ShiroRealm.getCurrentUser().getId());
            demand.setUserName(ShiroRealm.getCurrentUser().getUsername());
            demand.setCreateDate(DateUtil.getFormatCurrentDate());
            if (demand.getId() != null) {
                demandService.updateById(demand);
            } else {
                demandService.insert(demand);
            }
            return "redirect:page_query/1/5";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 条件分页查询，跳转至需求管理主页
     */
    @RequiresPermissions(value = {"demand"}, logical = Logical.OR)
    @RequestMapping(value = "/condition_query", method = RequestMethod.POST)
    public String conditionQuery(Model model, Demand demand) {
        EntityWrapper<Demand> ew = new EntityWrapper<>();
        ew.like("title", demand.getTitle());
        Page page = new Page(1, 5);
        demandService.selectPage(page, ew);
        model.addAttribute("page", page);
        model.addAttribute("title", demand.getTitle());
        return "Need/index";
    }

    /**
     * 分页查询数据
     */
    @RequiresPermissions(value = {"demand"}, logical = Logical.OR)
    @RequestMapping(value = "/page_query/{current}/{size}", method = RequestMethod.GET)
    public String pageQuery(Model model, @PathVariable("current") int current, @PathVariable("size") int size) {
        Page<Demand> page = new Page<>(current, size);
        EntityWrapper<Demand> ew = new EntityWrapper<>();
        ew.orderBy("level", false).orderBy("create_date", true);
        page = demandService.selectPage(page, ew);
        model.addAttribute("page", page);
        return "Need/index";
    }
}
