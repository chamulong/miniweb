package com.jcj.miniweb.service.impl;

import com.jcj.miniweb.entity.SysAuth;
import com.jcj.miniweb.repository.SysAuthRepo;
import com.jcj.miniweb.service.SysAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限业务层实现
 */
@Service("sysAuthService")
public class SysAuthServiceImpl implements SysAuthService
{
    @Autowired
    private SysAuthRepo sysAuthRepo;

    @Override
    public List<SysAuth> findAll()
    {
        return sysAuthRepo.findAll();
    }
}
