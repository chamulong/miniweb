package com.jcj.miniweb.security;

import com.jcj.miniweb.entity.SysAuth;
import com.jcj.miniweb.entity.SysUser;
import com.jcj.miniweb.repository.SysAuthRepo;
import com.jcj.miniweb.repository.SysRoleRepo;
import com.jcj.miniweb.repository.SysUserRepo;
import com.jcj.miniweb.service.SysAuthService;
import com.jcj.miniweb.service.SysRoleService;
import com.jcj.miniweb.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义用户加载类，用于验证用户，在验证通过的情况下，加载用户所对应的全部权限
 */
@Service
public class MyUserDetailsService implements UserDetailsService
{
    @Resource(name = "sysUserService")
    private SysUserService sysUserService;

    @Resource(name = "sysRoleService")
    private SysRoleService sysRoleService;

    @Resource(name="sysAuthService")
    private SysAuthService sysAuthService;

    @Autowired
    private HttpSession session;


    //重写加载用户的方法，实现用户验证、创建session、获取对应的全部权限
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        //根据账号名称、邮箱、手机号进行搜索（用的是JPA方式,参数是名称/邮箱/手机号之一），三者有其一，则验证通过
        SysUser sysUser=sysUserService.findByUsernameOrUseremailOrUsermobile(s,s,s);
        if (sysUser==null)
        {
            throw new UsernameNotFoundException("用户名/密码错误");
        }

        //根据登录的用户，创建session,方便其他应用
        session.setAttribute("userinfo",sysUser);

        //获取该用户对应角色的权限，如果角色是‘超级管理员’，则直接获取全部的权限
        List<SysAuth> sysAuths=new ArrayList<SysAuth>();
        if(sysUser.getSysRole().getName().equals("超级管理员"))//系统默认一个账号只对应
        {
            List<SysAuth> listAuth=sysAuthService.findAll();
            for (SysAuth sysAuth:listAuth)
            {
                sysAuths.add(sysAuth);
            }
        }
        else
        {
            for (SysAuth sysAuth:sysUser.getSysRole().getSysAuths())
            {
                sysAuths.add(sysAuth);
            }
        }

        //将权限信息添加到GrantedAuthority对象中，在后面进行权限验证时会使用GrantedAuthority对象
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for (SysAuth sysAuth:sysAuths)
        {
            if(sysAuth!=null&&sysAuth.getName()!=null)
            {
                GrantedAuthority grantedAuthority=new SimpleGrantedAuthority(sysAuth.getName());
                grantedAuthorities.add(grantedAuthority);
            }
        }

        return new User(sysUser.getUsername(),sysUser.getPassword(),grantedAuthorities);
    }
}
