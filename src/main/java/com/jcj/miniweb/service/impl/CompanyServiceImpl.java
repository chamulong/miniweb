package com.jcj.miniweb.service.impl;

import com.jcj.miniweb.entity.Company;
import com.jcj.miniweb.repository.CompanyRepo;
import com.jcj.miniweb.service.CompanyService;
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
 * 公司业务层实现
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService
{
    @Autowired
    private CompanyRepo companyRepo;//注入数据仓库层的接口

    //保存或更新数据
    public void save(Company company)
    {
        companyRepo.save(company);
    }

    //删除数据
    public void delete(Company company)
    {
        companyRepo.delete(company);
    }

    //基于主键删除数据
    @Transactional
    public void delete(String uuid)
    {
        companyRepo.deleteByUuid(uuid);
    }

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

    //简单分页查询
    public Page<Company> findAllSimplePage(Pageable pageable)
    {
        return companyRepo.findAll(pageable);
    }

    //带查询条件的分页查询
    public Page<Company> queryDynamic(Map<String,Object> reqMap, Pageable pageable)
    {
        Specification querySpecifi=new Specification<Company>()
        {
            @Override
            public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
            {
                List<Predicate> predicates = new ArrayList<>();
                if(!reqMap.get("cname").toString().equals(""))//公司名称，like 模糊查询
                {
                    predicates.add(criteriaBuilder.like(root.get("cname"),"%"+reqMap.get("cname").toString()+"%"));
                }
                if(!reqMap.get("legalpersonname").toString().equals(""))//法人姓名，精确查询
                {
                    predicates.add(criteriaBuilder.equal(root.get("legalpersonname"),reqMap.get("legalpersonname").toString()));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return this.companyRepo.findAll(querySpecifi,pageable);
    }
}
