package com.stone.user.service.impl;

import com.stone.model.entity.user.UserAddress;
import com.stone.user.mapper.UserAddressMapper;
import com.stone.user.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;
    @Override
    public List<UserAddress> findUserAddressList(Long id) {
        return userAddressMapper.getUserAddressListByUserId(id);
    }

    @Override
    public UserAddress getById(Long id) {
        return userAddressMapper.getById(id);

    }
}
