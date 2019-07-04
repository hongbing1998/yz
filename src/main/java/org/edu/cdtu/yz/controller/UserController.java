package org.edu.cdtu.yz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.edu.cdtu.yz.Relam.ShiroRealm;
import org.edu.cdtu.yz.bean.*;
import org.edu.cdtu.yz.query.PageQuery;
import org.edu.cdtu.yz.service.*;
import org.edu.cdtu.yz.util.AjaxResult;
import org.edu.cdtu.yz.util.DateUtil;
import org.edu.cdtu.yz.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController extends GlobalDefaultExceptionHandler {
    @Autowired
    public IUserService userService;

    @Autowired
    public IPathService iPathService;
    @Autowired
    public IActivityService iActivityService;
    @Autowired
    public IPolicyService iPolicyService;
    @Autowired
    public IRoleService roleService;
    @Autowired
    public ISchoolService schoolService;

    /**
     * 保存、修改 【区分id即可】
     *
     * @param user 传递的实体
     * @return Ajaxresult转换结果
     */
    @ResponseBody
    @RequiresPermissions(value = {"admin"}, logical = Logical.OR)
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody User user) {
        Map<String, Object> msp = new HashMap<>();

        try {
            if (user.getId() != "") {
                System.out.println("dscds");
                if (user.getPassword() != "") {
                    Object r = new SimpleHash("MD5", user.getPassword(), null, 1024);
                }
                String da = DateUtil.getFormatCurrentDate();
                user.setCreateDate(da);
                if (user.getPassword() == "") {
                    User uses = userService.selectOne(new EntityWrapper<User>().eq("id", user.getId()));
                    user.setPassword(uses.getPassword());
                }
                userService.updateById(user);

                if (user.getUsername() != null) {
                    String username = user.getUsername();
                    Path path = new Path();
                    path.setUserName(username);
                    iPathService.update(path, new EntityWrapper<Path>()
                            .eq("user_name", username)
                    );
                    Activity activity = new Activity();
                    activity.setUserName(username);
                    iActivityService.update(activity, new EntityWrapper<Activity>()
                            .eq("user_name", username)
                    );
                    Policy policy = new Policy();
                    policy.setUserName(username);
                    iPolicyService.update(policy, new EntityWrapper<Policy>()
                            .eq("user_name", username)
                    );
                }
            } else {
                School school = schoolService.selectById(user.getSchoolId());
                if (user.getPassword() == "") {
                    AjaxResult.me().setSuccess(false);
                }
                Object r = new SimpleHash("MD5", user.getPassword(), null, 1024);

                String m = r.toString();
                String da = DateUtil.getFormatCurrentDate();
                user.setCreateDate(da);
                user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                user.setPassword(m);
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                user.setSchoolName(school.getSchoolName());
                userService.insert(user);
            }

            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false);
        }
    }

    //删除对象信息
    @ResponseBody
    @RequiresPermissions(value = {"admin"}, logical = Logical.OR)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") String id) {
        try {
            userService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！" + e.getMessage());
        }
    }

    //获取用户
    @RequiresPermissions(value = {"admin"}, logical = Logical.OR)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AjaxResult get(@PathVariable("id") Long id) {
        return AjaxResult.me().setResultObj(userService.selectById(id));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "main";
    }

    //转跳到add.jsp
//    @RequiresPermissions(value = {"toadd"}, logical = Logical.OR)
//    @RolesAllowed({"ADMIN"})
    @RequiresPermissions(value = {"admin"}, logical = Logical.OR)
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(Model model) {
        model.addAttribute("roles", roleService.selectList(null));
        model.addAttribute("schools", schoolService.selectList(null));
        return "User/add";
    }

    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String login() {
        User user = ShiroRealm.getCurrentUser();
        //如果session中没有user，表示没登陆
        if (user == null) {
            return "redirect:/login.jsp";
        } else {
            return "index";
        }
    }

    @RequiresPermissions(value = {"admin"}, logical = Logical.OR)
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String get(String id, Model model) {
        model.addAttribute("user", userService.selectById(id));
        model.addAttribute("schools", schoolService.selectList(null));
        return "User/add";
    }
    //查看所有的员工信息
    @RequiresPermissions(value = {"admin"}, logical = Logical.OR)
//    @RolesAllowed({"ADMIN"})
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("users", userService.selectList(null));
        return "User/index";
    }


    /**
     * 分页查询数据：
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public AjaxResult json(@RequestBody PageQuery query) {
        Page<User> page = new Page<User>(query.getPage(), query.getRows());
        page = userService.selectPage(page);
        return AjaxResult.me().setResultObj(new PageList<User>(page.getPages(), page.getRecords()));
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AjaxResult login(@RequestBody User user) {
        String account = user.getAccount();
        String password = user.getPassword();
        boolean remeberMe = user.isRemeberMe();
        System.out.println(remeberMe);
        // 1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(account, password, remeberMe);
        // 3.执行登录方法
        try{
            subject.login(token);

        } catch (UnknownAccountException e){
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("账号或者密码错误");
        } catch (IncorrectCredentialsException e) {
            return AjaxResult.me().setSuccess(false).setMessage("账号或者密码错误");
        }
        return AjaxResult.me();
    }
}
