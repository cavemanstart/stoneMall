package com.stone.user.mapper;

import com.stone.model.entity.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserAddressMapper {
    List<UserAddress> getUserAddressListByUserId(Long id);

    UserAddress getById(Long id);
}
