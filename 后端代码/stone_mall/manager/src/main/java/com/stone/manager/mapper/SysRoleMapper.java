package com.stone.manager.mapper;

import com.stone.model.dto.system.SysRoleDto;
import com.stone.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMapper {
    List<SysRole> findPage(@Param("sysRoleDto") SysRoleDto sysRoleDto);

    void saveRole(@Param("sysRole") SysRole sysRole);

    void update(@Param("sysRole") SysRole sysRole);

    void deleteById(@Param("roleId") Long roleId);

    List<SysRole> getAll();

    List<Long> getRoleByUserId(Long userId);


}
