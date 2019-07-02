package org.edu.cdtu.yz.service;

import java.util.List;
import java.util.Map;

public interface IMenuService {
    public List<Map<String, Object>> getMenu(String username);
}
