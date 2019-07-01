package org.edu.cdtu.yz.controller;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {


    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public Map<String, String> defaultExceptionHandler(HttpServletRequest req, Exception e) {
        Map<String, String> result = new HashMap<>();
        result.put("status", "403");
        result.put("msg", "对不起，你没有访问权限！");
        return result;
    }
}

