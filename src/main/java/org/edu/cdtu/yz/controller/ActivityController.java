package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.edu.cdtu.yz.Relam.ShiroRealm;
import org.edu.cdtu.yz.bean.Activity;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.IActivityService;
import org.edu.cdtu.yz.util.AjaxResult;
import org.edu.cdtu.yz.util.DateUtil;
import org.edu.cdtu.yz.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    public IActivityService activityService;

    /**
     * 保存、修改 【区分id即可】
     * @param activity  传递的实体
     * @return Ajaxresult转换结果
     */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Activity activity){
        try {
            if(activity.getId()!=null){
                    activityService.updateById(activity);
            }else{
                activity.setCreateDate(DateUtil.getFormatCurrentDate());
                activity.setUserId(ShiroRealm.getCurrentUser().getId());
                activity.setUserName(ShiroRealm.getCurrentUser().getUsername());
                    activityService.insert(activity);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    //删除对象信息
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            activityService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public AjaxResult get(@PathVariable("id") Long id)
    {
        return AjaxResult.me().setResultObj(activityService.selectById(id));
    }


    //查看所有的员工信息
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public AjaxResult list() {
        return AjaxResult.me().setResultObj(activityService.selectList(null));
    }


    /**
    * 分页查询数据：
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody PageQuery query) {
        Page<Activity> page = new Page<Activity>(query.getPage(),query.getRows());
        page = activityService.selectPage(page);
        return AjaxResult.me().setResultObj(new PageList<Activity>(page.getPages(), page.getRecords()));
    }
}
