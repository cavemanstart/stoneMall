package com.stone.manager.mapper;

import com.stone.model.dto.system.AssginMenuDto;
import com.stone.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper {

    List<SysMenu> findAll();

    void save(SysMenu sysMenu);

    void update(SysMenu sysMenu);

    void removeById(Long id);

    int countMenuChildren(Long id);

    List<Long> findMenuIdListByRoleId(Long roleId);

    void removeMenuByRoleId(Long roleId);

    void doAssign(AssginMenuDto assginMenuDto);

    List<SysMenu> userMenu(Long id);

    void updateIsHalf(Long parentId);
}
