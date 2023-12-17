package com.stone.manager.service;

import com.stone.model.dto.system.AssginMenuDto;
import com.stone.model.entity.system.SysMenu;
import com.stone.model.vo.system.SysMenuVo;

import java.util.List;
import java.util.Map;

public interface SysMenuService {

    List<SysMenu> findNodes();

    void save(SysMenu sysMenu);

    void update(SysMenu sysMenu);

    void removeById(Long id);

    Map<String, Object> findSysRoleMenuByRoleId(Long roleId);

    void doAssign(AssginMenuDto assginMenuDto);

    List<SysMenuVo> userMenus();

}
