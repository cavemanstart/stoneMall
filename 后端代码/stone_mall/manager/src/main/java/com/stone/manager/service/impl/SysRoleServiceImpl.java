package com.stone.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stone.manager.mapper.SysMenuMapper;
import com.stone.manager.mapper.SysRoleMapper;
import com.stone.manager.service.SysRoleService;
import com.stone.model.dto.system.SysRoleDto;
import com.stone.model.entity.system.SysMenu;
import com.stone.model.entity.system.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysMenuMapper menuMapper;
    @Override
    public PageInfo<SysRole> findPage(Integer current, Integer size, SysRoleDto sysRoleDto) {
        PageHelper.startPage(current,size);
        List<SysRole> roleList = sysRoleMapper.findPage(sysRoleDto);
        PageInfo<SysRole> pageInfo = new PageInfo<>(roleList);
        return pageInfo;
    }

    @Override
    public void saveRole(SysRole sysRole) {
        sysRoleMapper.saveRole(sysRole);
    }

    @Override
    public void update(SysRole sysRole) {
        sysRoleMapper.update(sysRole);
    }

    @Override
    public void deleteById(Long roleId) {
        sysRoleMapper.deleteById(roleId);
    }

    @Override
    public Map<String, Object> getAllRoles(Long userId) {
        Map<String, Object> map = new HashMap<>();
        List<SysRole> roleList =  sysRoleMapper.getAll();
        List<Long> userRoleList = sysRoleMapper.getRoleByUserId(userId);
        map.put("allRolesList",roleList);
        map.put("sysUserRoles",userRoleList);
        return map;
    }

}
