package com.jcj.miniweb.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 账号类实体
 */
@Entity
@Table(name = "sysuser")
public class SysUser implements UserDetails
{
    //借用hibernate的方法自动生成uuid
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid")
    @Column(length = 32)
    private String uuid;

    @Column(length = 100)
    private String username;

    @Column(length = 100)
    private String password;

    @Column(length = 100)
    private String useremail;

    @Column(length = 100)
    private String usermobile;

    @Column(length = 32)
    private String sysroleid;

    //referencedColumnName对应的是关联表对应的列
    @OneToOne
    @JoinColumn(name="sysroleid",referencedColumnName="uuid",insertable = false, updatable = false)
    private SysRole sysRole;

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUsermobile() {
        return usermobile;
    }

    public void setUsermobile(String usermobile) {
        this.usermobile = usermobile;
    }

    public String getSysroleid(){return sysroleid;}

    public void setSysroleid(String sysroleid){this.sysroleid = sysroleid;}

    public SysRole getSysRole()
    {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole)
    {
        this.sysRole = sysRole;
    }

    /**
     * 通过用户表实体来完成用户认证功能，需要实现getAuthorities方法内容，将定义的角色列表添加到授权的列表内
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();
        SysRole role=getSysRole();
        auths.add(new SimpleGrantedAuthority(role.getName()));

        return auths;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

}
