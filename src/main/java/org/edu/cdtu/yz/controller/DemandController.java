package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.edu.cdtu.yz.bean.Demand;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.IDemandService;
import org.edu.cdtu.yz.util.AjaxResult;
import org.edu.cdtu.yz.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/demand")
public class DemandController {
    @Autowired
    public IDemandService demandService;

    /**
     * 保存数据，数据有id则根据id更新，无id则新增数据
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody Demand demand) {
        try {
            demand.setCreateDate(DateUtil.getFormatCurrentDate());
            if (demand.getId() != null) {
                demandService.updateById(demand);
            } else {
                demandService.insert(demand);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！" + e.getMessage());
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
     * 多表分页查询数据
     */
    @RequestMapping(value = "/page_query", method = RequestMethod.POST)
    public AjaxResult pageQuery(@RequestBody PageQuery query) {
        Page<Map<String, Object>> page = demandService.selectDemandsPage(query);
        return AjaxResult.me().setResultObj(page);
    }
}
