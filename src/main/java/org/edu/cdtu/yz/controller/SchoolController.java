package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.edu.cdtu.yz.Relam.ShiroRealm;
import org.edu.cdtu.yz.bean.School;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.ISchoolService;
import org.edu.cdtu.yz.util.AjaxResult;
import org.edu.cdtu.yz.util.DateUtil;
import org.edu.cdtu.yz.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private ISchoolService schoolService;

    /**
     * 保存、修改 【区分id即可】
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody School school) {
        try {
            school.setSchoolOperateTime(DateUtil.getFormatCurrentDate());
            if (school.getId() != null) {
                schoolService.updateById(school);
            } else {
                school.setSchoolOperateId(ShiroRealm.getCurrentUser().getId());
                school.setSchoolOperateName(ShiroRealm.getCurrentUser().getUsername());
                schoolService.insert(school);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！" + e.getMessage());
        }
    }

    /**
     * 删除对象信息
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") String id) {
        try {
            schoolService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！" + e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AjaxResult get(@PathVariable("id") String id) {
        return AjaxResult.me().setResultObj(schoolService.selectById(id));
    }


    /**
     * 查看所有的员工信息
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public AjaxResult list() {
        return AjaxResult.me().setResultObj(schoolService.selectList(null));
    }


    /**
     * 分页查询数据：
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public AjaxResult json(@RequestBody PageQuery query) {
        Page<School> page = new Page<>(query.getPage(), query.getRows());
        page = schoolService.selectPage(page);
        return AjaxResult.me().setResultObj(new PageList<>(page.getPages(), page.getRecords()));
    }
}
