package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.edu.cdtu.yz.Relam.ShiroRealm;
import org.edu.cdtu.yz.bean.Employment;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.IEmploymentService;
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
@RequestMapping("/employment")
public class EmploymentController {
    @Autowired
    public IEmploymentService employmentService;

    /**
     * 保存、修改 【区分id即可】
     * @param employment  传递的实体
     * @return Ajaxresult转换结果
     */
    @RequiresPermissions(value = {"employment"}, logical = Logical.OR)
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public String save(Employment employment, Model model) {
        try {
            if(employment.getId()!=null){
                employment.setCreateDate(DateUtil.getFormatCurrentDate());
                employment.setUserName(ShiroRealm.getCurrentUser().getUsername());
                employment.setUserId(ShiroRealm.getCurrentUser().getId());
                    employmentService.updateById(employment);
            }else{
                employment.setCreateDate(DateUtil.getFormatCurrentDate());
                employment.setUserName(ShiroRealm.getCurrentUser().getUsername());
                employment.setUserId(ShiroRealm.getCurrentUser().getId());
                    employmentService.insert(employment);
            }
            ;
            List<Employment> employmentList = employmentService.selectList(new EntityWrapper<Employment>()
                    .orderBy("create_date", false)
            );
            model.addAttribute("advers", employmentList);
            return "/Adver/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "/error/unAuth";
        }
    }

    /**
     * 查询所有招聘
     *
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/employmentList", method = RequestMethod.GET)
    public AjaxResult list() {
        return AjaxResult.me().setResultObj(employmentService.selectList(null));
    }

    //删除对象信息
    @RequiresPermissions(value = {"employment"}, logical = Logical.OR)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id, Model model) {
        try {
            employmentService.deleteById(id);
            return "/Adver/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "/error/unAuth";
        }
    }

    //获取用户
    @RequiresPermissions(value = {"employment"}, logical = Logical.OR)
    @RequestMapping(value = "/Adver/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") String id, Model model) {
        Employment employment = employmentService.selectById(id);
        model.addAttribute("Adver", employment);
        return "/Adver/show";
    }

    //获取用户
    @RequiresPermissions(value = {"apply"}, logical = Logical.OR)
    @RequestMapping(value = "/apply/{applyId}/{id}", method = RequestMethod.GET)
    public String applyGet(@PathVariable("applyId") String applyId, @PathVariable("id") String id, Model model)
    {
        model.addAttribute("Adver", employmentService.selectById(id));
        model.addAttribute("applyId", applyId);
        return "/helpteacher/applyshow";
    }


    //条件查看所有的招聘信息
    @RequiresPermissions(value = {"employment"}, logical = Logical.OR)
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String qurelist(String title, Model model) {
        List<Employment> employmentList = employmentService.selectList(new EntityWrapper<Employment>()
                .like("title", title).orderBy("create_date", false)
        );
        model.addAttribute("advers", employmentList);
        return "/Adver/index";
    }


    /**
    * 分页查询数据：
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequiresPermissions(value = {"employment"}, logical = Logical.OR)
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public String json(PageQuery query, Model model) {
        Page<Employment> page = new Page<Employment>(query.getPage(),query.getRows());
        page = employmentService.selectPage(page);
        model.addAttribute("pageAdver", new PageList<Employment>(page.getPages(), page.getRecords()));
        return "/Adver/show";
    }
}
