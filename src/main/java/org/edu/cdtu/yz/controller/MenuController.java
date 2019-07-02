package org.edu.cdtu.yz.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.edu.cdtu.yz.bean.User;
import org.edu.cdtu.yz.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    IMenuService menuService;

    @RequestMapping("/list")
    public Map<String, Object> getMenu() {
        try {
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getPrincipal();
            System.out.println(user.getId());
            List<Map<String, Object>> menu = menuService.getMenu(user.getId());
            Map<String, Object> config = new HashMap<>();
            config.put("id", 1);
            config.put("menu", menu);
            return config;
        } catch (Exception e) {
            return null;
        }
    }
}
