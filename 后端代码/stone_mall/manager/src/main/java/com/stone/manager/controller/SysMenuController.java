package com.stone.manager.controller;
import com.stone.manager.service.SysMenuService;
import com.stone.model.entity.system.SysMenu;
import com.stone.model.vo.common.Result;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("admin/system/sysMenu")
public class SysMenuController {
    @Autowired
    private SysMenuService menuService;
    //菜单列表
    @GetMapping("findNodes")
    public Result findNodes(){
        List<SysMenu> list =  menuService.findNodes();
        return Result.ok(list);
    }
    //菜单添加
    @PostMapping("save")
    public Result saveMenu(@RequestBody SysMenu sysMenu){
        //向sysMenu中添加数据
        menuService.save(sysMenu);
        return Result.ok(null);
    }
    //菜单修改
    @PutMapping("update")
    public Result update(@RequestBody SysMenu sysMenu){
        menuService.update(sysMenu);
        return Result.ok(null);
    }
    //删除
    @DeleteMapping("removeById/{id}")
    public Result removeById(@PathVariable("id") Long id){
        menuService.removeById(id);
        return Result.ok(null);
    }

}
