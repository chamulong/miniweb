package com.jcj.miniweb.service.impl;

import com.jcj.miniweb.entity.SysRole;
import com.jcj.miniweb.repository.SysRoleRepo;
import com.jcj.miniweb.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 角色业务层实现
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService
{
    @Autowired
    private SysRoleRepo sysRoleRepo;

    public SysRole findByUuid(String uuid)
    {
        return sysRoleRepo.findByUuid(uuid);
    }


}
