package org.edu.cdtu.yz.service.impl;

import org.edu.cdtu.yz.mapper.MenuMapper;
import org.edu.cdtu.yz.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuserviceImpl implements IMenuService {
    @Autowired
    MenuMapper menuMapper;

    public List<Map<String, Object>> getMenu(String username) {
        System.out.println(username);
        List<Map<String, Object>> allList = menuMapper.getAllMenu(username);
        List<Map<String, Object>> parentList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> menu : allList) {
            if (menu.get("parentId").equals("0")) {
                parentList.add(menu);
            } else {
                childList.add(menu);
            }
        }
        for (Map<String, Object> pMenu : parentList) {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> m = new HashMap<>();
            for (Map<String, Object> cMenu : childList) {
                if (cMenu.get("parentId").equals(pMenu.get("menuId"))) {
                  

                    list.add(cMenu);
                }
            }
            pMenu.put("childNode", list);

        }
        return parentList;

    }
}