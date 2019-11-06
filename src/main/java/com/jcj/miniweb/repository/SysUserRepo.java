package com.jcj.miniweb.repository;

import com.jcj.miniweb.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 账号数据仓库接口
 */
public interface SysUserRepo extends JpaRepository<SysUser,Long>, JpaSpecificationExecutor
{
    //使用SpringDataJPA方法定义查询,根据用户名/邮件/手机号，查询用户信息
    SysUser findByUsernameOrUseremailOrUsermobile(String username,String email,String mobile);


}
