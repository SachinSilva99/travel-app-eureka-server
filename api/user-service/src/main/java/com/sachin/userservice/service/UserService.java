package com.sachin.userservice.service;

import com.sachin.userservice.dto.UserDTO;
import com.sachin.userservice.exception.NotFoundException;

import java.util.List;

public interface UserService {
    String createUser(UserDTO userDTO);

    void update(String userId, UserDTO userDTO) throws NotFoundException;

    void delete(String userId) throws NotFoundException;

    UserDTO get(String id) throws NotFoundException;

    List<UserDTO> getAll();

    void login(String username, String password);

}
