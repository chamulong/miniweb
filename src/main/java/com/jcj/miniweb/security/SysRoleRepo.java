package com.jcj.miniweb.security;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 角色数据仓库接口
 */
public interface SysRoleRepo extends JpaRepository<SysRole,Long>
{
}
