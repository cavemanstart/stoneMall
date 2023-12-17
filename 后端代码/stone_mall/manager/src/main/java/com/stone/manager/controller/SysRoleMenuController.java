package com.stone.manager.controller;

import com.github.pagehelper.PageInfo;
import com.stone.manager.service.SysMenuService;
import com.stone.manager.service.SysRoleService;
import com.stone.model.dto.system.AssginMenuDto;
import com.stone.model.dto.system.SysRoleDto;
import com.stone.model.entity.system.SysRole;
import com.stone.model.vo.common.Result;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin/system/sysRoleMenu")
public class SysRoleMenuController {
    @Autowired
    private SysMenuService menuService;
    //查询所有菜单和角色分配过的菜单id列表
    @GetMapping("findSysRoleMenuByRoleId/{roleId}")
    public Result findSysRoleMenuByRoleId(@PathVariable("roleId") Long roleId){
        Map<String ,Object> map =  menuService.findSysRoleMenuByRoleId(roleId);
        return Result.ok(map);
    }
    //保存角色分配的菜单数据
    @PostMapping("doAssign")
    public Result doAssign(@RequestBody AssginMenuDto assginMenuDto){
        menuService.doAssign(assginMenuDto);
        return Result.ok(null);
    }
}
