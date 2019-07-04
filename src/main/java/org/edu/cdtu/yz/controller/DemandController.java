package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.edu.cdtu.yz.Relam.ShiroRealm;
import org.edu.cdtu.yz.bean.Demand;
import org.edu.cdtu.yz.bean.School;
import org.edu.cdtu.yz.service.IDemandService;
import org.edu.cdtu.yz.service.ISchoolService;
import org.edu.cdtu.yz.util.AjaxResult;
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
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("schools", schoolService.selectList(null));
        return "Need/add";
    }

    /**
     * 保存数据，数据有id则根据id更新，无id则新增数据
     */
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
            return "redirect:index";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 根据id删除数据
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id, Model model) {
        try {
            demandService.deleteById(id);
            Page page = new Page(1, 5);
            page = demandService.selectPage(page, getOrderByEw("level", false));
            model.addAttribute("page", page);
            return "Need/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 根据id查询单条数据
     */
    @RequestMapping(value = "query/{id}", method = RequestMethod.GET)
    public AjaxResult get(@PathVariable("id") String id) {
        return AjaxResult.me().setResultObj(demandService.selectById(id));
    }

    /**
     * 单表条件查询多条数据
     */
    @RequestMapping(value = "/condition_quey", method = RequestMethod.POST)
    public String conditionQuery(Demand demand, Model model) {
        EntityWrapper<Demand> ew = new EntityWrapper<>();
        ew.like("title", demand.getTitle());
        Page page = new Page(1, 5);
        demandService.selectPage(page, ew);
        model.addAttribute("page", page);
        return "Need/index";
    }

    /**
     * 初始进入需求管理
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        Page page = new Page(1, 5);
        page = demandService.selectPage(page, getOrderByEw("level", false));
        model.addAttribute("page", page);
        return "Need/index";
    }

    /**
     * 多表分页查询数据
     */
    @RequestMapping(value = "/page_query", method = RequestMethod.POST)
    public String pageQuery(Model model, Page page) {
        page = demandService.selectPage(page, getOrderByEw("level", false));
        model.addAttribute("page", page);
        return "Need/index";
    }

    private EntityWrapper<Demand> getOrderByEw(String columns, Boolean isAsc) {
        EntityWrapper<Demand> ew = new EntityWrapper<>();
        ew.orderBy(columns, isAsc);// 按level降序排列
        return ew;
    }
}
