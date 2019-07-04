package org.edu.cdtu.yz.controller;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler
    public String exceptiona(Exception ex) {
        Map<String, String> result = new HashMap<>();
    
        return "error/error";
    }

}




