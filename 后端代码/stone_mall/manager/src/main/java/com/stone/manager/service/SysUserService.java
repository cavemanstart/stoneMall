package com.stone.manager.service;

import com.github.pagehelper.PageInfo;
import com.stone.model.dto.system.AssginRoleDto;
import com.stone.model.dto.system.LoginDto;
import com.stone.model.dto.system.SysUserDto;
import com.stone.model.entity.system.SysUser;
import com.stone.model.vo.system.LoginVo;

public interface SysUserService {
    LoginVo login(LoginDto loginDto);

    SysUser getUserInfo(String token);

    void logout(String token);

    PageInfo<SysUser> getPage(Integer current, Integer size, SysUserDto sysUserDto);

    void saveUser(SysUser sysUser);

    void updateUser(SysUser sysUser);

    void deleteById(Long id);

    void doAssign(AssginRoleDto assginRoleDto);
}
