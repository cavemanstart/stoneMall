package com.stone.manager.service.impl;

import com.stone.exception.StoneException;
import com.stone.manager.mapper.SysMenuMapper;
import com.stone.manager.service.SysMenuService;
import com.stone.manager.util.TreeBuilderUtil;
import com.stone.model.dto.system.AssginMenuDto;
import com.stone.model.entity.system.SysMenu;
import com.stone.model.entity.system.SysUser;
import com.stone.model.vo.system.SysMenuVo;
import com.stone.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper menuMapper;
    @Override
    public List<SysMenu> findNodes() {
        List<SysMenu> allMenus= menuMapper.findAll();
        List<SysMenu> topMenus = TreeBuilderUtil.buildMenuTree(allMenus);
        return topMenus;
    }

    @Override
    public void save(SysMenu sysMenu) {
        menuMapper.save(sysMenu);
        //修改父菜单的is_half = 1
        if(sysMenu.getParentId()!=0)
            menuMapper.updateIsHalf(sysMenu.getParentId());
    }

    @Override
    public void update(SysMenu sysMenu) {
        menuMapper.update(sysMenu);
    }

    @Override
    public void removeById(Long id) {
        int count = menuMapper.countMenuChildren(id);
        if(count>0) throw new StoneException(261,"含有子菜单无法删除");
        menuMapper.removeById(id);
    }

    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {
        Map<String, Object> map = new HashMap<>();
        List<SysMenu> allMenus = findNodes();
        List<Long> menuIdList = menuMapper.findMenuIdListByRoleId(roleId);
        map.put("sysMenuList",allMenus);
        map.put("roleMenuIds",menuIdList);
        return map;
    }

    @Override
    public void doAssign(AssginMenuDto assginMenuDto) {
        // 先删除原来的菜单
        menuMapper.removeMenuByRoleId(assginMenuDto.getRoleId());
        // 再重新分配菜单
        List<Map<String, Number>> menuInfo = assginMenuDto.getMenuIdList();
        if(menuInfo!=null && menuInfo.size()>0){
            menuMapper.doAssign(assginMenuDto);
        }
    }

    @Override
    public List<SysMenuVo> userMenus() {
        //获取当前用户id
        SysUser sysUser = AuthContextUtil.get();
        Long id = sysUser.getId();
        // 查询可以操作的菜单
        List<SysMenu> menuList =  menuMapper.userMenu(id);
        // 将菜单转化为树形结构
        List<SysMenuVo> sysMenuVos = TreeBuilderUtil.buildMenuVoTree(menuList);
        return sysMenuVos;
    }
}
