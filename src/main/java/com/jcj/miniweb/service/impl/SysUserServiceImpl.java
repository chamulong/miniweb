package com.jcj.miniweb.service.impl;

import com.jcj.miniweb.entity.SysRole;
import com.jcj.miniweb.entity.SysUser;
import com.jcj.miniweb.repository.SysUserRepo;
import com.jcj.miniweb.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 账号业务层
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService
{
    @Autowired
    private SysUserRepo sysUserRepo;

    //新添加用户
    public void save(SysUser sysUser)
    {
        //密码进行BCrypt强哈希加密
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String hashPassword=passwordEncoder.encode(sysUser.getPassword());
        sysUser.setPassword(hashPassword);
        sysUserRepo.save(sysUser);
    }

    @Override
    public SysUser findByUsernameOrUseremailOrUsermobile(String username, String email, String mobile)
    {
        return sysUserRepo.findByUsernameOrUseremailOrUsermobile(username,email,mobile);
    }


}
