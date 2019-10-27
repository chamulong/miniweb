package com.jcj.miniweb.security;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 账号数据仓库接口
 */
public interface SysUserRepo extends JpaRepository<SysUser,Long>
{
    //使用SpringDataJPA方法定义查询,根据用户名/邮件/手机号，查询用户信息
    SysUser findByUsernameOrUseremailOrUsermobile(String username,String email,String mobile);
}
