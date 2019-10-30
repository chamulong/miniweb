package com.jcj.miniweb.service;

import com.jcj.miniweb.entity.SysRole;

/**
 * 角色业务层接口
 */
public interface SysRoleService
{
    //根据uuid查找角色
    public SysRole findByUuid(String uuid);
}
