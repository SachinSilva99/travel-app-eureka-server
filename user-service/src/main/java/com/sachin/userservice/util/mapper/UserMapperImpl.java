package com.sachin.userservice.util.mapper;

import com.sachin.userservice.dto.UserDTO;
import com.sachin.userservice.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final ModelMapper mapper;

    @Override
    public User toUser(UserDTO userDTO) {
        return mapper.map(userDTO, User.class);
    }

    @Override
    public UserDTO toUserDto(User user) {
        return mapper.map(user, UserDTO.class);
    }
}
