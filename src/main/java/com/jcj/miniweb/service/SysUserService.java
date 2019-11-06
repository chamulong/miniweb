package com.jcj.miniweb.service;

import com.jcj.miniweb.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * 账号业务层接口
 */
public interface SysUserService
{
    //新添加账号
    void save(SysUser sysUser);

    //根据账号/邮箱/手机号三者之一查询账号
    SysUser findByUsernameOrUseremailOrUsermobile(String username,String email,String mobile);

    //带查询条件的分页查询
    Page<SysUser> queryDynamic(Map<String,Object> reqMap, Pageable pageable);

}
