package org.edu.cdtu.yz.controller;

import org.edu.cdtu.yz.query.UserQuery;
import org.edu.cdtu.yz.service.IUserService;
import org.edu.cdtu.yz.bean.User;
import org.edu.cdtu.yz.util.AjaxResult;
import org.edu.cdtu.yz.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public IUserService userService;

    /**
     * 保存、修改 【区分id即可】
     * @param user  传递的实体
     * @return Ajaxresult转换结果
     */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody User user){
        try {
            if(user.getId()!=null){
                    userService.updateById(user);
            }else{
                    userService.insert(user);
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
            userService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User get(@PathVariable("id")Long id)
    {
        return userService.selectById(id);
    }


    //查看所有的员工信息
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<User> list(){
        return userService.selectList(null);
    }


    /**
    * 分页查询数据：
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<User> json(@RequestBody UserQuery query) {
        Page<User> page = new Page<User>(query.getPage(),query.getRows());
        page = userService.selectPage(page);
        return new PageList<User>(page.getTotal(),page.getRecords());
    }
}
