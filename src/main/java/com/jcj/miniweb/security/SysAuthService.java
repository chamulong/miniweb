package com.jcj.miniweb.security;

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
