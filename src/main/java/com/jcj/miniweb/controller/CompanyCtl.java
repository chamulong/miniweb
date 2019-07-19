package com.jcj.miniweb.controller;

import com.jcj.miniweb.entity.Company;
import com.jcj.miniweb.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author： 江成军
 * @Date：Create on  2019/7/19 21:59
 * @Description：控制类，公司
 */
@Controller
@RequestMapping("/company")
public class CompanyCtl
{
  @Autowired
  private CompanyService companyService;

  @RequestMapping("/findAll")
  @ResponseBody
  public List<Company> findAll()
  {
    //查询全部数据（利用JpaRepository已封装的方法）
    return companyService.findAll();
  }

  @RequestMapping("/findByNativeSQL")
  @ResponseBody
  public List<Company> findByNativeSQL(@RequestParam String cname)
  {
    //查询全部数据（执行原生SQL查询语句）
    return companyService.findByNativeSQL(cname);
  }

  @RequestMapping("/updateByName")
  @ResponseBody
  public void updateByName(@RequestParam String caddress,String cname)
  {
    //更新数据（执行原生SQL语句的更新）
    companyService.updateByName(caddress,cname);
  }

  @RequestMapping("/findByCname")
  @ResponseBody
  public Company findByCname(@RequestParam String cname)
  {
    //解析方法名的查询（Spring Data JPA框架）
    return companyService.findByCname(cname);
  }

  @RequestMapping("/findByCnameAndLegalpersonname")
  @ResponseBody
  public Company findByCnameAndLegalpersonname(@RequestParam String cname,String legalpersonname)
  {
    //解析方法名的多条件查询（Spring Data JPA框架）
    return companyService.findByCnameAndLegalpersonname(cname,legalpersonname);
  }



}
