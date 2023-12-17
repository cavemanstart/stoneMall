package com.stone.manager.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stone.manager.service.SysUserService;
import com.stone.model.dto.system.AssginRoleDto;
import com.stone.model.dto.system.SysUserDto;
import com.stone.model.entity.system.SysUser;
import com.stone.model.vo.common.Result;
import com.stone.model.vo.h5.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PrivilegedExceptionAction;

@RestController
@RequestMapping("admin/system/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService userService;

    //用户分页查询
    @GetMapping("findByPage/{current}/{size}")
    public Result findPage(@PathVariable("current") Integer current, @PathVariable("size") Integer size, SysUserDto sysUserDto){
        PageInfo<SysUser> pageInfo = userService.getPage(current,size,sysUserDto);
        return Result.ok(pageInfo);
    }
    //添加用户
    @PostMapping("saveSysUser")
    public Result saveUser(@RequestBody SysUser sysUser){
        userService.saveUser(sysUser);
        return Result.ok(null);
    }
    //用户修改
    @PutMapping("updateSysUser")
    public Result updateUser(@RequestBody SysUser sysUser){
        userService.updateUser(sysUser);
        return Result.ok(null);
    }
    //用户删除
    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable("id") Long id){
        userService.deleteById(id);
        return Result.ok(null);
    }
    //分配角色
    @PostMapping("doAssign")
    public Result doAssign(@RequestBody AssginRoleDto assginRoleDto){
        userService.doAssign(assginRoleDto);
        return Result.ok(null);
    }
}
