package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.edu.cdtu.yz.Relam.ShiroRealm;
import org.edu.cdtu.yz.bean.Policy;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.IPolicyService;
import org.edu.cdtu.yz.util.AjaxResult;
import org.edu.cdtu.yz.util.DateUtil;
import org.edu.cdtu.yz.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/policy")
public class PolicyController {
    @Autowired
    public IPolicyService policyService;

    /**
     * 保存、修改 【区分id即可】
     * @param policy  传递的实体
     * @return Ajaxresult转换结果
     */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public String save( Policy policy,Model model){
        try {
            if(policy.getId()!=null){
                policy.setCreateData(DateUtil.getFormatCurrentDate());
                    policyService.updateById(policy);
            }else{
                policy.setCreateData(DateUtil.getFormatCurrentDate());
                policy.setUserId(ShiroRealm.getCurrentUser().getId());
                policy.setUserName(ShiroRealm.getCurrentUser().getUsername());
                policy.setSchoolName(ShiroRealm.getCurrentUser().getSchoolName());
                policy.setSchoolId(ShiroRealm.getCurrentUser().getSchoolId());
                    policyService.insert(policy);
            }
            Page<Policy> page = new Page<Policy>(0,10);
            page = policyService.selectPage(page);
            model.addAttribute("resultObj",new PageList<Policy>(page.getPages(), page.getCurrent(),page.getRecords()));
            return "Policy/index";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //删除对象信息
    @ResponseBody
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") String id){
        try {
            policyService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //去修改页面
    @RequestMapping(value = "toedit/{id}",method = RequestMethod.GET)
    public String toedit(@PathVariable("id") String id,Model model)
    {
        model.addAttribute("policy",policyService.selectById(id));
        return "Policy/edit";
    }
    //去添加页面
    @RequestMapping(value = "/toadd",method = RequestMethod.GET)
    public String toadd()
    {
        return "Policy/add";
    }

    //去查看页面
    @RequestMapping(value = "toshow/{id}",method = RequestMethod.GET)
    public String toshow(@PathVariable("id") String id,Model model)
    {
        model.addAttribute("policy",policyService.selectById(id));
        return "Policy/show";
    }

    //查看所有的员工信息
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public AjaxResult list() {
        return AjaxResult.me().setResultObj(policyService.selectList(null));
    }


    /**
    * 分页查询数据：
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ResponseBody
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody PageQuery query) {
        Page<Policy> page = new Page<Policy>(query.getPage(),query.getRows());
        page = policyService.selectPage(page);
        return AjaxResult.me().setResultObj(new PageList<Policy>(page.getPages(), page.getRecords()));
    }

    @RequestMapping(value = "/toPindex",method = RequestMethod.GET)
    public String  toIndex(Model model) {
        Page<Policy> page = new Page<Policy>(0,10);
        page = policyService.selectPage(page);
        model.addAttribute("resultObj",new PageList<Policy>(page.getPages(), page.getCurrent(),page.getRecords()));
        return "Policy/index";
    }
}
