package com.stone.manager.service;

import com.github.pagehelper.PageInfo;
import com.stone.model.dto.system.SysRoleDto;
import com.stone.model.entity.system.SysRole;

import java.util.Map;

public interface SysRoleService {
    PageInfo<SysRole> findPage(Integer current, Integer size, SysRoleDto sysRoleDto);

    void saveRole(SysRole sysRole);

    void update(SysRole sysRole);

    void deleteById(Long roleId);

    Map<String, Object> getAllRoles(Long userId);

}
