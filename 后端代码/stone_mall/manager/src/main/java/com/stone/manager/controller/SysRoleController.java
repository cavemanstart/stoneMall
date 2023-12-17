package com.stone.manager.controller;

import com.github.pagehelper.PageInfo;
import com.stone.log.anotation.Log;
import com.stone.manager.service.SysRoleService;
import com.stone.model.dto.system.SysRoleDto;
import com.stone.model.entity.system.SysRole;
import com.stone.model.vo.common.Result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("admin/system/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;
    //角色列表
    @PostMapping("findByPage/{current}/{size}")
    public Result findPage(@PathVariable("current") Integer current, @PathVariable("size") Integer size,
                           @RequestBody SysRoleDto sysRoleDto){
        PageInfo<SysRole> pageModel = sysRoleService.findPage(current,size, sysRoleDto);
        return Result.ok(pageModel);
    }
    //查询所有角色
    @GetMapping("findAllRoles/{userId}")
    public Result findAllRoles(@PathVariable("userId") Long userId){
        Map<String, Object> map =  sysRoleService.getAllRoles(userId);

        return Result.ok(map);
    }
    //添加角色
    @Log(title = "角色添加",businessType = 0)
    @PostMapping("saveSysRole")
    public Result saveRole(@RequestBody SysRole sysRole){
        sysRoleService.saveRole(sysRole);
        return Result.ok(null);
    }
    //修改角色
    @Log(title = "角色修改",businessType = 0)
    @PutMapping("updateSysRole")
    public Result update(@RequestBody SysRole sysRole){
        sysRoleService.update(sysRole);
        return Result.ok(null);
    }
    //删除用户
    @Log(title = "角色删除",businessType = 0)
    @DeleteMapping("deleteById/{roleId}")
    public Result deleteById(@PathVariable("roleId") Long roleId){
        sysRoleService.deleteById(roleId);
        return Result.ok(null);
    }
}
