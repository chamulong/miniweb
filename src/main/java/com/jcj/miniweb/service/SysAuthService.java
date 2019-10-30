package com.jcj.miniweb.service;

import com.jcj.miniweb.entity.SysAuth;

import java.util.List;

/**
 * 权限业务层接口
 */
public interface SysAuthService
{
    //查找全部权限
    List<SysAuth> findAll();
}
