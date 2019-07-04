package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.edu.cdtu.yz.Relam.ShiroRealm;
import org.edu.cdtu.yz.bean.Demand;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.IDemandService;
import org.edu.cdtu.yz.service.ISchoolService;
import org.edu.cdtu.yz.util.AjaxResult;
import org.edu.cdtu.yz.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
            demand.setUserId(ShiroRealm.getCurrentUser().getId());
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
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") String id) {
        try {
            demandService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！" + e.getMessage());
        }
    }

    /**
     * 根据id查询单条数据
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AjaxResult get(@PathVariable("id") String id) {
        return AjaxResult.me().setResultObj(demandService.selectById(id));
    }

    /**
     * 单表条件查询多条数据
     */
    @RequestMapping(value = "/condition_quey", method = RequestMethod.POST)
    public AjaxResult conditionQuery(@RequestBody(required = false) Demand demand) {
        EntityWrapper<Demand> demandEntity = new EntityWrapper<>(demand);
        return AjaxResult.me().setResultObj(demandService.selectList(demandEntity));
    }

    /**
     * 初始进入需求管理
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String pageQuery(Model model) {
        PageQuery query = new PageQuery(1, 5);// 默认第一页，5条数据
        Page<Map<String, Object>> page = demandService.selectDemandsPage(query);
        model.addAttribute("page", page);
        return "Need/index";
    }

    /**
     * 多表分页查询数据
     */
    @RequestMapping(value = "/page_query", method = RequestMethod.POST)
    public String pageQuery(Model model, PageQuery query) {
        Page<Map<String, Object>> page = demandService.selectDemandsPage(query);
        model.addAttribute("page", page);
        return "Need/index";
    }
}
