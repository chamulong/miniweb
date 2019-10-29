package com.jcj.miniweb.service;

import com.jcj.miniweb.repository.SysUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账号服务层
 */
@Service
public class SysUserService
{
    @Autowired
    private SysUserRepo sysUserRepo;
}
