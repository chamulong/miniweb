package com.jcj.miniweb.service;

import com.jcj.miniweb.entity.Company;
import com.jcj.miniweb.repository.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 公司业务层接口
 */
public interface CompanyService
{

  //保存或更新数据
  public void save(Company company);


  //删除数据
  public void delete(Company company);

  //基于主键删除数据
  public void delete(String uuid);

  //查询全部数据
  public List<Company> findAll();

  //执行原生SQL语句的查询
  public List<Company> findByNativeSQL(String companyname);

  //执行原生SQL语句的更新
  @Transactional
  public void updateByName(String companyaddress, String companyname);

  //解析方法名的查询
  public Company findByCname(String companyname);

  //解析方法名的多条件查询
  public Company findByCnameAndLegalpersonname(String companyname, String legalpersonname);

  //简单分页查询
  public Page<Company> findAllSimplePage(Pageable pageable);

  //带查询条件的分页查询
  public Page<Company> queryDynamic(Map<String,Object> reqMap, Pageable pageable);

}
