package com.stone.user.service;

import com.stone.model.entity.user.UserAddress;

import java.util.List;

public interface UserAddressService {
    List<UserAddress> findUserAddressList(Long id);

    UserAddress getById(Long id);
}
