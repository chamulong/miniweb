package com.jcj.miniweb.security;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 权限数据仓库接口
 */
public interface SysAuthRepo extends JpaRepository<SysAuth,Long>
{

}
