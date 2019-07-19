package com.jcj.miniweb.service;

import com.jcj.miniweb.entity.Company;
import com.jcj.miniweb.repository.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author： 江成军
 * @Date：Create on  2019/7/19 11:33
 * @Description：无
 */
@Service
public class CompanyService
{
  @Autowired
  private CompanyRepo companyRepo;//注入数据仓库层的接口

  //查询全部数据
  public List<Company> findAll()
  {
    return companyRepo.findAll();
  }

  //执行原生SQL语句的查询
  public List<Company> findByNativeSQL(String companyname)
  {
    return companyRepo.findByNativeSQL(companyname);
  }

  //执行原生SQL语句的更新
  @Transactional
  public void updateByName(String companyaddress, String companyname)
  {
    companyRepo.updateByName(companyaddress, companyname);
  }

  //解析方法名的查询
  public Company findByCname(String companyname)
  {
    return companyRepo.findByCname(companyname);
  }

  //解析方法名的多条件查询
  public Company findByCnameAndLegalpersonname(String companyname, String legalpersonname)
  {
    return companyRepo.findByCnameAndLegalpersonname(companyname, legalpersonname);
  }

}
