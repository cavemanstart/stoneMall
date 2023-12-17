package com.stone.manager.controller;

import com.stone.manager.service.SysMenuService;
import com.stone.manager.service.SysUserService;
import com.stone.manager.service.ValidateCodeService;
import com.stone.model.dto.system.LoginDto;
import com.stone.model.entity.system.SysMenu;
import com.stone.model.entity.system.SysUser;
import com.stone.model.vo.common.Result;
import com.stone.model.vo.system.LoginVo;
import com.stone.model.vo.system.SysMenuVo;
import com.stone.model.vo.system.ValidateCodeVo;
import com.stone.utils.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "用户接口")
@RestController
@RequestMapping("admin/system/index")
public class IndexController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ValidateCodeService codeService;
    @Autowired
    private SysMenuService menuService;
    @PostMapping("login")
    public Result login(@RequestBody LoginDto loginDto){
        LoginVo loginVo =  sysUserService.login(loginDto);
        if(loginVo!=null)
            return  Result.ok(loginVo);
        return Result.fail(null);
    }
    @GetMapping("generateValidateCode")
    public Result generateValidateCode(){
        ValidateCodeVo validateCodeVo = codeService.generateValidateCode();
        return Result.ok(validateCodeVo);
    }
    @GetMapping("getUserInfo")
    public Result getUserInfo(){
        return Result.ok(AuthContextUtil.get());
    }
    @GetMapping("logout")
    public Result logout(@RequestHeader(name="token") String token){
        sysUserService.logout(token);
        return Result.ok(null);
    }
    //查询用户能操作的菜单
    @GetMapping("menus")
    public Result menus(){
        List<SysMenuVo>  list = menuService.userMenus();
        return Result.ok(list);
    }
}
