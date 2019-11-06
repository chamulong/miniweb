package com.jcj.miniweb.service.impl;

import com.jcj.miniweb.entity.SysUser;
import com.jcj.miniweb.repository.SysUserRepo;
import com.jcj.miniweb.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 账号业务层
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService
{
    @Autowired
    private SysUserRepo sysUserRepo;

    //新添加用户
    @Override
    public void save(SysUser sysUser)
    {
        //密码进行BCrypt强哈希加密
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String hashPassword=passwordEncoder.encode(sysUser.getPassword());
        sysUser.setPassword(hashPassword);
        sysUserRepo.save(sysUser);
    }

    @Override
    public SysUser findByUsernameOrUseremailOrUsermobile(String username, String email, String mobile)
    {
        return sysUserRepo.findByUsernameOrUseremailOrUsermobile(username,email,mobile);
    }

    //带查询条件的分页查询
    @Override
    public Page<SysUser> queryDynamic(Map<String,Object> reqMap, Pageable pageable)
    {
        Specification querySpecifi=new Specification<SysUser>()
        {
            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
            {
                List<Predicate> predicates = new ArrayList<>();
                if(!(reqMap.get("username")==null||reqMap.get("username").toString().equals("")))//账号名称，like 模糊查询
                {
                    predicates.add(criteriaBuilder.like(root.get("username"),"%"+reqMap.get("username").toString()+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return this.sysUserRepo.findAll(querySpecifi,pageable);
    }


}
