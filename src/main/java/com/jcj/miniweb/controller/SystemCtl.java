package com.jcj.miniweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 江成军
 * @Date: 2019/07/27 10:38
 * @Description: 主框架控制类，处理非功能模块的页面跳转
 */
@Controller
public class SystemCtl
{
    @RequestMapping("/")
    public String showIndex()
    {
        //根URL默认跳转到框架主页面,就是templates文件夹下的index.html
        return "index";
    }
}