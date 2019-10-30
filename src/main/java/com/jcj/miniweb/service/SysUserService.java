package com.jcj.miniweb.service;

import com.jcj.miniweb.entity.SysUser;

/**
 * 账号业务层接口
 */
public interface SysUserService
{
    //新添加账号
    public void save(SysUser sysUser);

    //根据账号/邮箱/手机号三者之一查询账号
    SysUser findByUsernameOrUseremailOrUsermobile(String username,String email,String mobile);

}
