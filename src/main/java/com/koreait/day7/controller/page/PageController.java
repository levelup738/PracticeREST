package com.koreait.day7.controller.page;

import com.koreait.day7.service.AdminMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/pages")
public class PageController {

    @Autowired
    private AdminMenuService adminMenuService;

    @RequestMapping(path = {""})
    public ModelAndView index(){
        return new ModelAndView("/pages/main")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "main");
    }
    @RequestMapping("/user")
    public ModelAndView user() {
        return new ModelAndView("/pages/user")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "user");
    }
    @RequestMapping("/user/{id}/orderInfo")
    public ModelAndView orderInfo(@PathVariable Long id) {
        return new ModelAndView("/pages/orderInfo")
                .addObject("id", id)
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "orderInfo");
    }
}
