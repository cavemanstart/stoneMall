package com.stone.user.service;

import com.stone.model.dto.h5.UserLoginDto;
import com.stone.model.dto.h5.UserRegisterDto;
import com.stone.model.vo.h5.UserInfoVo;

public interface UserInfoService {
    void register(UserRegisterDto userRegisterDto);

    String login(UserLoginDto userLoginDto);

    UserInfoVo getCurrentUserInfo(String token);
}
