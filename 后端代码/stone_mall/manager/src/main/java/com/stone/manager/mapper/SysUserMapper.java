package com.stone.manager.mapper;


import com.stone.model.dto.system.SysUserDto;
import com.stone.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Mapper
public interface SysUserMapper{
    SysUser getUserByUsername(@Param("username") String username);

    List<SysUser> getList(@Param("userDto") SysUserDto sysUserDto);

    void saveUser(SysUser sysUser);

    void update(SysUser sysUser);

    void deleteById(Long id);

    void removeByUserId(Long userId);


    void assign(Long userId, Long roleId);
}
