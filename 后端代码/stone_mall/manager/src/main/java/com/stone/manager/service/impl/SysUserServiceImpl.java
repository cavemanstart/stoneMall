package com.stone.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stone.exception.StoneException;
import com.stone.manager.mapper.SysUserMapper;
import com.stone.manager.service.SysUserService;
import com.stone.model.dto.h5.UserRegisterDto;
import com.stone.model.dto.system.AssginRoleDto;
import com.stone.model.dto.system.LoginDto;
import com.stone.model.dto.system.SysUserDto;
import com.stone.model.entity.system.SysUser;
import com.stone.model.vo.common.ResultCodeEnum;
import com.stone.model.vo.system.LoginVo;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public LoginVo login(LoginDto loginDto) {
        String captcha = loginDto.getCaptcha();
        String codeKey = loginDto.getCodeKey();
        String code = redisTemplate.opsForValue().get("user:validate" + codeKey);
        if(StrUtil.isEmpty(code) || !StrUtil.equalsIgnoreCase(code, captcha))
            throw new StoneException(ResultCodeEnum.VALIDATECODE_ERROR);
        redisTemplate.delete("user:validate" + codeKey);
        String userName = loginDto.getUserName();
        String password = loginDto.getPassword();
        SysUser sysUser =  sysUserMapper.getUserByUsername(userName);
        if(sysUser==null)
            throw new StoneException(ResultCodeEnum.LOGIN_ERROR);
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!md5Password.equals(sysUser.getPassword()))
            throw new StoneException(ResultCodeEnum.LOGIN_ERROR);
        String token = UUID.randomUUID().toString().replaceAll("-","");
        redisTemplate.opsForValue().set("user:login"+token, JSON.toJSONString(sysUser),7, TimeUnit.DAYS);
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        return loginVo;
    }

    @Override
    public SysUser getUserInfo(String token) {
        String userInfo = redisTemplate.opsForValue().get("user:login"+token);
        SysUser sysUser = JSON.parseObject(userInfo, SysUser.class);
        return sysUser;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login"+token);
    }

    @Override
    public PageInfo<SysUser> getPage(Integer current, Integer size, SysUserDto sysUserDto) {
        PageHelper.startPage(current,size);
        List<SysUser> list =  sysUserMapper.getList(sysUserDto);
        final PageInfo<SysUser> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void saveUser(SysUser sysUser) {
        SysUser user = sysUserMapper.getUserByUsername(sysUser.getUserName());
        if(user!=null) throw new StoneException(274,"username被占用");
        String code = DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes());
        sysUser.setPassword(code);
        sysUser.setStatus(1);
        sysUserMapper.saveUser(sysUser);
    }

    @Override
    public void updateUser(SysUser sysUser) {
        sysUserMapper.update(sysUser);
    }

    @Override
    public void deleteById(Long id) {
        sysUserMapper.deleteById(id);
    }

    @Override
    public void doAssign(AssginRoleDto assginRoleDto) {
        Long userId = assginRoleDto.getUserId();
        List<Long> roleIdList = assginRoleDto.getRoleIdList();
        sysUserMapper.removeByUserId(userId);
        for (Long roleId : roleIdList){
            sysUserMapper.assign(userId,roleId);
        }
    }
}
