package com.jcj.miniweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录相关的控制层
 */
@Controller
public class LoginCtl
{
    @RequestMapping(value="/login")
    public String login(){return "/login";}

    @RequestMapping(value = "/index")
    public String index()
    {
        return "/index";
    }
}
