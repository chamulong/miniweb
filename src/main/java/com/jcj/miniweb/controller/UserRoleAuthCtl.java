package com.jcj.miniweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.jcj.miniweb.entity.SysRole;
import com.jcj.miniweb.entity.SysUser;
import com.jcj.miniweb.entity.Ztree;
import com.jcj.miniweb.service.SysAuthService;
import com.jcj.miniweb.service.SysRoleService;
import com.jcj.miniweb.service.SysUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 涉及到账号、角色、权限的相关功能
 */
@Controller
@RequestMapping("/security")
public class UserRoleAuthCtl
{
    @Resource(name="sysUserService")
    private SysUserService sus;

    @Resource(name="sysRoleService")
    private SysRoleService srs;

    @Resource(name="sysAuthService")
    private SysAuthService sas;

    @RequestMapping("/listrole")
    public String findAllRole(Model model)
    {
        /**
         * 查询全部的角色，然后跳转到页面，利用了Thymeleaf模板中的迭代器进行页面数据的输出
         */
        List<SysRole> list=srs.findALL();
        model.addAttribute("sysRoles",list);
        return "/ListUserRoleAuth";//这里指的是页面：/ListUserRoleAuth.html
    }

    @PostMapping("/listuser")
    @ResponseBody
    public String queryDynamic(@RequestBody Map<String,Object> reqMap)
    {
        /**
         * 系统账号分页查询
         */

        //固定不变的两个分页参数
        int page=0;
        if(reqMap.get("page").toString()!=null){page= Integer.parseInt(reqMap.get("page").toString());}
        int size=3;
        if(reqMap.get("size").toString()!=null){size= Integer.parseInt(reqMap.get("size").toString());}

        Sort sort=new Sort(Sort.Direction.DESC,"username");
        Page<SysUser> pageinfo=sus.queryDynamic(reqMap, PageRequest.of(page,size,sort));
        List<SysUser> sysUsers =pageinfo.getContent();
        JSONObject result = new JSONObject();
        result.put("rows", sysUsers);
        result.put("total",pageinfo.getTotalElements());
        return result.toJSONString();
    }



    @RequestMapping("/listauth")
    @ResponseBody
    public List<Ztree> findALLToZtree(String roleid)
    {
        /**
         * 返回全部权限（zTree结构形式的树形节点）
         */
        return sas.findALLToZtree(roleid);
    }

    //保存角色
    @PostMapping("/saveRole")
    @ResponseBody
    public String save(SysRole sysRole)
    {
        srs.save(sysRole);
        return "OK";
    }

    //删除角色
    @PostMapping("/deleteRole")
    @ResponseBody
    public String deleteRole(String uuid)
    {
        srs.deleteByUuid(uuid);
        srs.deleteMaptabByUuid(uuid);
        return "OK";
    }

    //保存子节点（需要判断是否有重复，如果没有重复,保存信息,id是当前选定节点的id，name是需要新增加子节点的名称）
    @PostMapping("/saveChildAuth")
    @ResponseBody
    public String saveChildAuth(@RequestParam int id, String name)
    {
        return sas.saveChildAuth(id,name);
    }

    //根据节点的id删除节点及子节点
    @PostMapping("/deleteByChild")
    @ResponseBody
    public String deleteByChild(@RequestParam int id)
    {
        sas.deleteByChild(id);
        return "OK";
    }

    //保存角色对应的权限信息,,其中‘authinfo’是以$分割的节点id字符串
    @PostMapping("/editRole")
    @ResponseBody
    public String editRole(@RequestParam String uuid,String authinfo)
    {
        sas.editRole(uuid,authinfo);
        return "OK";
    }

}
