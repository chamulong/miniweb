package com.jcj.miniweb.repository;

import com.jcj.miniweb.entity.SysAuth;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 权限数据仓库接口
 */
public interface SysAuthRepo extends JpaRepository<SysAuth,Long>
{

}
