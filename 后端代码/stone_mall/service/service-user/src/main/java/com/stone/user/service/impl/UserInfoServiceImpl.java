package com.stone.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.stone.exception.StoneException;
import com.stone.model.dto.h5.UserLoginDto;
import com.stone.model.dto.h5.UserRegisterDto;
import com.stone.model.entity.user.UserInfo;
import com.stone.model.vo.h5.UserInfoVo;
import com.stone.user.mapper.UserInfoMapper;
import com.stone.user.service.UserInfoService;
import com.stone.utils.AuthContextUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RedisTemplate<String , String> redisTemplate;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(UserRegisterDto userRegisterDto) {
        //数据预校验
        if(StringUtils.isEmpty(userRegisterDto.getUsername()) ||
                StringUtils.isEmpty(userRegisterDto.getPassword()) ||
                StringUtils.isEmpty(userRegisterDto.getNickName()) ||
                StringUtils.isEmpty(userRegisterDto.getCode())
            ){
            throw new StoneException(291,"数据缺失");
        }
        //校验验证码
        String code = redisTemplate.opsForValue().get(userRegisterDto.getUsername());
        if(!userRegisterDto.getCode().equals(code)){
            throw new StoneException(292,"验证码错误");
        }
        //判断用户是否已经注册
        UserInfo userInfo = userInfoMapper.getByUsername(userRegisterDto.getUsername());
        if(userInfo!=null){
            throw new StoneException(293,"用户名已存在");
        }
        //新用户写入表
        userInfo = new UserInfo();
        userInfo.setUsername(userRegisterDto.getUsername());
        userInfo.setPassword(DigestUtils.md5DigestAsHex(userRegisterDto.getPassword().getBytes()));
        userInfo.setNickName(userRegisterDto.getNickName());
        userInfo.setStatus(1);
        userInfo.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        userInfoMapper.save(userInfo);
        //删除redis验证码
        redisTemplate.delete(userRegisterDto.getUsername());
    }

    @Override
    public String login(UserLoginDto userLoginDto) {
        if(StringUtils.isEmpty(userLoginDto.getUsername()) ||
                StringUtils.isEmpty(userLoginDto.getPassword())) {
            throw new StoneException(291,"数据缺失");
        }
        UserInfo userInfo = userInfoMapper.getByUsername(userLoginDto.getUsername());
        if(userInfo==null){
            throw new StoneException(298,"用户名不存在");
        }
        if(userInfo.getStatus()==0){
            throw new StoneException(296,"用户被禁用");
        }
        String md5Password = DigestUtils.md5DigestAsHex(userLoginDto.getPassword().getBytes());
        String token;
        if(md5Password.equals(userInfo.getPassword())){
            token = UUID.randomUUID().toString().replaceAll("-", "");
            redisTemplate.opsForValue().set(token, JSON.toJSONString(userInfo),180, TimeUnit.MINUTES);
        }else{
            throw new StoneException(299,"密码错误");
        }
        return token;
    }

    @Override
    public UserInfoVo getCurrentUserInfo(String token) {
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        if(userInfo!=null){
            UserInfoVo userInfoVo = new UserInfoVo();
            BeanUtils.copyProperties(userInfo, userInfoVo);
            return userInfoVo;
        }
        throw new StoneException(208,"userInfo 为空");
    }
}
