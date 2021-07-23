package com.zhouyouwu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Administrator
 */
@Controller
public class PageController {

    @RequestMapping("goToMain.do")
    public ModelAndView goToMain(){
        return new ModelAndView("login");
    }
}
