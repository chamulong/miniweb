package com.jcj.miniweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.jcj.miniweb.entity.Company;
import com.jcj.miniweb.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author： 江成军
 * @Date：Create on  2019/7/19 21:59
 * @Description：控制类，公司
 */
@Controller
@RequestMapping("/company")
public class CompanyCtl
{
  @Resource(name = "companyService")
  private CompanyService cs;

  /*****************************************************
   * 传统做法,非Restful设计风格
   ****************************************************/
  @RequestMapping("/showIndex")
  public String showIndex()
  {
    //注意，这里面的跳转页面有别于Thymeleaf模板，这是普通跳转（放在public文件夹下），需要redirect修饰，并且带后缀文件名，Thymeleaf不需要
    //资源文件中，resources、static、public这三个文件夹可以直接访问，而templates文件夹是安全文件夹，不能直接访问
    return "redirect:/index.html";
  }

  @RequestMapping("/findAll")
  @ResponseBody
  public List<Company> findAll()
  {
    //查询全部数据（利用JpaRepository已封装的方法）
    return cs.findAll();
  }

  @RequestMapping("/save")
  @ResponseBody
  public void save(Company company)
  {
    //保存或更新数据（利用JpaRepository已封装的方法）
    //company.setUuid(UUID.randomUUID().toString().replace("-", ""));
    cs.save(company);
  }

  @RequestMapping("/delete")
  @ResponseBody
  public void delete(Company company)
  {
    //删除数据（利用JpaRepository已封装的方法）
    cs.delete(company);
  }

  @RequestMapping("/findByNativeSQL")
  @ResponseBody
  public List<Company> findByNativeSQL(@RequestParam String cname)
  {
    //查询全部数据（执行原生SQL查询语句）
    return cs.findByNativeSQL(cname);
  }

  @RequestMapping("/updateByName")
  @ResponseBody
  public void updateByName(@RequestParam String caddress,String cname)
  {
    //更新数据（执行原生SQL语句的更新）
    cs.updateByName(caddress,cname);
  }

  @RequestMapping("/findByCname")
  @ResponseBody
  public Company findByCname(@RequestParam String cname)
  {
    //解析方法名的查询（Spring Data JPA框架）
    return cs.findByCname(cname);
  }

  @RequestMapping("/findByCnameAndLegalpersonname")
  @ResponseBody
  public Company findByCnameAndLegalpersonname(@RequestParam String cname,String legalpersonname)
  {
    //解析方法名的多条件查询（Spring Data JPA框架）
    return cs.findByCnameAndLegalpersonname(cname,legalpersonname);
  }

  /*****************************************************
   * Restful设计风格
   ****************************************************/
  @RequestMapping(value="/company",method = RequestMethod.GET)
  @ResponseBody
  public List<Company> listCompany()
  {
    //GET方式，查询数据列表
    return cs.findAll();
  }

  @RequestMapping(value="/company",method = RequestMethod.POST)
  @ResponseBody
  public void saveCompany()
  {
    //POST方式，保存数据
   Company company=new Company();
    company.setUuid("u1111111111111111111111111111111");
    company.setCname("深圳腾讯");
    company.setCaddress("深圳创业大道一号");
    company.setCurl("http://www.qq.com");
    company.setCemail("12345@qq.com");
    company.setCpersonnum(30000);
    company.setTotalincome(5000.0000f);
    company.setLegalpersonname("马化腾");
    company.setLegalpersonmobil("13911111111");
    company.setBusinesslicense("lince111111");
    company.setCbrief("深圳市腾讯计算机系统有限公司成立于1998年11月 [1]  ，由马化腾、张志东、许晨晔、陈一丹、曾李青五位创始人共同创立");
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    company.setCreatetime(sdf.format(d));
    cs.save(company);
  }

  @RequestMapping(value="/company/{uuid}",method = RequestMethod.DELETE)
  @ResponseBody
  public void deleteCompany(@PathVariable String uuid)
  {
    //POST方式，删除数据
    cs.delete(uuid);
  }

  //指定客户列表页面,由于默认不能直接访问templates文件夹下的页面，所以通过跳转的方式进行访问
  @RequestMapping(value = "/listcompanyhtml")
  public String listcompanyhtml()
  {
    return "/ListCompany";//对应的是页面文件("templates"文件夹下的ListCompany.html文件)
  }

  //访问新增页面
  @RequestMapping(value = "/addcompanyhtml")
  public String addcompanyhtml()
  {
    return "/AddCompany";//对应的是页面文件("templates"文件夹下的AddCompany.html文件)
  }

  @PostMapping("/findAllSimplePage")
  @ResponseBody
  public String findAllSimplePage(@RequestBody Map<String,Object> reqMap)
  {
    /**
     *@Author 江成军
     *@Description 简单分页查询
     *@Date 2019/09/29 16:35
     **/
    //固定不变的两个分页参数
    int page=0;
    if(reqMap.get("page").toString()!=null){page= Integer.parseInt(reqMap.get("page").toString());}
    int size=2;
    if(reqMap.get("size").toString()!=null){size= Integer.parseInt(reqMap.get("size").toString());}

    Sort sort=new Sort(Sort.Direction.DESC,"uuid");//按照UUID排序
    Page<Company> pageinfo=cs.findAllSimplePage(PageRequest.of(page,size,sort));
    List<Company> companies =pageinfo.getContent();
    JSONObject result = new JSONObject();//maven中配置alibaba的fastjson依赖

    //"rows"和"total"这两个属性是为前端列表插件"bootstrap-table"服务的
    result.put("rows", companies);
    result.put("total",pageinfo.getTotalElements());
    return result.toJSONString();
  }

  @PostMapping("/queryDynamic")
  @ResponseBody
  public String queryDynamic(@RequestBody Map<String,Object> reqMap)
  {
    /**
     *@Author 江成军
     *@Description 简单分页查询
     *@Date 2019/09/29 16:35
     **/
    //固定不变的两个分页参数
    int page=0;
    if(reqMap.get("page").toString()!=null){page= Integer.parseInt(reqMap.get("page").toString());}
    int size=2;
    if(reqMap.get("size").toString()!=null){size= Integer.parseInt(reqMap.get("size").toString());}

    Sort sort=new Sort(Sort.Direction.ASC,"uuid");//按照UUID排序
    Page<Company> pageinfo=cs.queryDynamic(reqMap,PageRequest.of(page,size,sort));
    List<Company> companies =pageinfo.getContent();
    JSONObject result = new JSONObject();//maven中配置alibaba的fastjson依赖

    //"rows"和"total"这两个属性是为前端列表插件"bootstrap-table"服务的
    result.put("rows", companies);
    result.put("total",pageinfo.getTotalElements());
    return result.toJSONString();
  }

  @RequestMapping("/deleteByUuid")
  @ResponseBody
  public String deleteByUuid(String uuid)
  {
    //删除记录
    cs.delete(uuid);
    return "OK";
  }


}
