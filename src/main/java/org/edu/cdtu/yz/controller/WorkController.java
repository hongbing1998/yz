package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.edu.cdtu.yz.Relam.ShiroRealm;
import org.edu.cdtu.yz.bean.Policy;
import org.edu.cdtu.yz.bean.Work;
import org.edu.cdtu.yz.bean.Work;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.IWorkService;
import org.edu.cdtu.yz.util.AjaxResult;
import org.edu.cdtu.yz.util.DateUtil;
import org.edu.cdtu.yz.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/work")
public class WorkController {
    @Autowired
    public IWorkService workService;

    /**
     * 保存、修改 【区分id即可】
     * @param work  传递的实体
     * @return Ajaxresult转换结果
     */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public String save(Work work, Model model){
        try {
            if(work.getId()!=null){
                work.setCreateDate(DateUtil.getFormatCurrentDate());
                workService.updateById(work);
            }else{
                work.setCreateDate(DateUtil.getFormatCurrentDate());
                work.setUserId(ShiroRealm.getCurrentUser().getId());
                work.setUserName(ShiroRealm.getCurrentUser().getUsername());
                work.setSchoolName(ShiroRealm.getCurrentUser().getSchoolName());
                work.setSchoolId(ShiroRealm.getCurrentUser().getSchoolId());
                workService.insert(work);
            }
            Page<Work> page = new Page<Work>(0,10);
            page = workService.selectPage(page);
            model.addAttribute("resultObj",new PageList<Work>(page.getPages(), page.getCurrent(),page.getRecords()));
            return "Work/index";
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
            workService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public AjaxResult get(@PathVariable("id") String id)
    {
        return AjaxResult.me().setResultObj(workService.selectById(id));
    }


    //查看所有的员工信息
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public AjaxResult list() {
        return AjaxResult.me().setResultObj(workService.selectList(null));
    }


    /**
    * 分页查询数据：
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody PageQuery query) {
        Page<Work> page = new Page<Work>(query.getPage(),query.getRows());
        page = workService.selectPage(page);
        return AjaxResult.me().setResultObj(new PageList<Work>(page.getPages(), page.getRecords()));
    }

    @RequestMapping(value = "/toindex",method = RequestMethod.GET)
    public String  toIndex(Model model) {
        Page<Work> page = new Page<Work>(0,10);
        page = workService.selectPage(page);
        model.addAttribute("resultObj",new PageList<Work>(page.getPages(), page.getCurrent(),page.getRecords()));
        return "Work/index";
    }

    //去修改页面
    @RequestMapping(value = "toedit/{id}",method = RequestMethod.GET)
    public String toedit(@PathVariable("id") String id,Model model)
    {
        model.addAttribute("work",workService.selectById(id));
        return "Work/edit";
    }
    //去添加页面
    @RequestMapping(value = "/toadd",method = RequestMethod.GET)
    public String toadd()
    {
        return "Work/add";
    }
}
