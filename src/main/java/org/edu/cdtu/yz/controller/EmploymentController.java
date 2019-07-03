package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.edu.cdtu.yz.Relam.ShiroRealm;
import org.edu.cdtu.yz.bean.Employment;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.IEmploymentService;
import org.edu.cdtu.yz.util.AjaxResult;
import org.edu.cdtu.yz.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/employment")
public class EmploymentController {
    @Autowired
    public IEmploymentService employmentService;

    /**
     * 保存、修改 【区分id即可】
     * @param employment  传递的实体
     * @return Ajaxresult转换结果
     */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public String save( Employment employment){
        try {
            if(employment.getId()!=null){
                    employmentService.updateById(employment);
            }else{
                    employment.setUserName(ShiroRealm.getCurrentUser().getUsername());
                    employmentService.insert(employment);
            }
            return "redirect:/Adver/index.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error/unAuth.jsp";
        }
    }

    //删除对象信息
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id){
        try {
            employmentService.deleteById(id);
            return "redirect:/Adver/index.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error/unAuth.jsp";
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String get(@PathVariable("id") Long id,Model model)
    {
        model.addAttribute("Adver",employmentService.selectById(id));
        return "redirect:/Adver/show.jsp";
    }


    //查看所有的员工信息
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("allAdver",employmentService.selectList(null));
        return "redirect:/Adver/index.jsp";
    }


    /**
    * 分页查询数据：
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public String json(PageQuery query , Model model) {
        Page<Employment> page = new Page<Employment>(query.getPage(),query.getRows());
        page = employmentService.selectPage(page);
        model.addAttribute("pageAdver",new PageList<Employment>(page.getPages(), page.getRecords()));
        return "redirect:/Adver/show.jsp";
    }
}
