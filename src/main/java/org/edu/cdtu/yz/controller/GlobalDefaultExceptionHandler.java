package org.edu.cdtu.yz.controller;


import org.edu.cdtu.yz.util.AjaxResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler
    public AjaxResult exceptiona(Exception ex) {
        Map<String, String> result = new HashMap<>();
        result.put("status", "403");
        result.put("msg", "对不起，你没有访问权限！");
        return AjaxResult.me().setSuccess(false).setResultObj(result);
    }
}




