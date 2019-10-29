package com.jcj.miniweb.service;

import com.jcj.miniweb.repository.SysAuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 权限服务层
 */
@Service
public class SysAuthService
{
    @Autowired
    private SysAuthRepo sysAuthRepo;
}
