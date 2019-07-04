package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.edu.cdtu.yz.Relam.ShiroRealm;
import org.edu.cdtu.yz.bean.Employment;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.IEmploymentService;
import org.edu.cdtu.yz.util.DateUtil;
import org.edu.cdtu.yz.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
            model.addAttribute("allAdver", employmentService.selectList(null));
            return "/Adver/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "/error/unAuth";
        }
    }

    //删除对象信息
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id, Model model) {
        try {
            employmentService.deleteById(id);
            model.addAttribute("allAdver", employmentService.selectList(null));
            return "/Adver/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "/error/unAuth";
        }
    }

    //获取用户
    @RequestMapping(value = "/Adver/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") String id, Model model) {
        model.addAttribute("Adver", employmentService.selectById(id));
        return "/Adver/show";
    }

    //获取用户
    @RequestMapping(value = "/apply/{applyId}/{id}", method = RequestMethod.GET)
    public String applyGet(@PathVariable("applyId") String applyId, @PathVariable("id") String id, Model model)
    {
        model.addAttribute("Adver", employmentService.selectById(id));
        model.addAttribute("applyId", applyId);
        return "/helpteacher/applyshow";
    }


//    //查看所有的员工信息
//    @RequestMapping(value = "/list",method = RequestMethod.GET)
//    public String list(Model model) {
//        model.addAttribute("allAdver",employmentService.selectList(null));
//        return "/Adver/index";
//    }

    //条件查看所有的员工信息
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String qurelist(String title, Model model) {
        List<Employment> employmentList = employmentService.selectList(new EntityWrapper<Employment>()
                .like("title", title)
        );
        model.addAttribute("advers", employmentList);
        return "/Adver/index";
    }


    /**
    * 分页查询数据：
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public String json(PageQuery query, Model model) {
        Page<Employment> page = new Page<Employment>(query.getPage(),query.getRows());
        page = employmentService.selectPage(page);
        model.addAttribute("pageAdver", new PageList<Employment>(page.getPages(), page.getRecords()));
        return "/Adver/show";
    }
}
