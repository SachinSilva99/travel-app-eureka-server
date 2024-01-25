package com.sachin.userservice.util.mapper;

import com.sachin.userservice.dto.UserDTO;
import com.sachin.userservice.entity.User;

public interface UserMapper {
    User toUser(UserDTO userDTO);
    UserDTO toUserDto(User user);
}
