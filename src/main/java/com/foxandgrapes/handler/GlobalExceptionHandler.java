package com.foxandgrapes.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ModelAndView someException(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "服务器发生了某种错误");
        mv.addObject("ex", e);
        mv.setViewName("default");
        return mv;
    }
}
