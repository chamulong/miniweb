package com.jcj.miniweb.repository;

import com.jcj.miniweb.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 角色数据仓库接口
 */
public interface SysRoleRepo extends JpaRepository<SysRole,Long>
{
    //根据uuid查找角色角色信息
    SysRole findByUuid(String uuid);

    //根据uuid，删除角色
    @Modifying
    @Query(value = "delete from sysrole where uuid=?1",nativeQuery = true)
    void deleteByUuid(String uuid);

    //根据角色uuid，删除角色权限关联表中角色对应的记录
    @Modifying
    @Query(value = "delete from sysrole_sys_auths where sys_role_uuid=?1",nativeQuery = true)
    void deleteMaptabByUuid(String uuid);
}
