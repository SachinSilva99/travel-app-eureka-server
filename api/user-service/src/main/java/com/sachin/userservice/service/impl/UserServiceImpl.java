package com.sachin.userservice.service.impl;

import com.sachin.userservice.dto.UserDTO;
import com.sachin.userservice.entity.User;
import com.sachin.userservice.exception.DuplicateException;
import com.sachin.userservice.exception.NotFoundException;
import com.sachin.userservice.repo.UserRepo;
import com.sachin.userservice.service.UserService;
import com.sachin.userservice.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String createUser(UserDTO dto) {
        try {
            User user = mapper.toUser(dto);
            return userRepo.save(user).getUserId();
        } catch (DuplicateKeyException e) {
            throw new DuplicateException(e.getMessage());
        }
    }

    @Override
    public void update(String userId, UserDTO dto) throws NotFoundException {
        Optional<User> byId = userRepo.findById(userId);
        if (byId.isEmpty()) {
            throw new NotFoundException("user : " + userId + " is not found");
        }

        User user = byId.get();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setAddress(dto.getAddress());
        user.setEmail(dto.getEmail());
        user.setGender(dto.getGender());
        user.setNicPassportNumber(dto.getNicPassportNumber());
        user.setUserType(dto.getUserType());
        if (dto.getNicPassportBackImg() != null) {
            user.setNicPassportBackImg(dto.getNicPassportBackImg());
        }
        if (dto.getNicPassportFrontImg() != null) {
            user.setNicPassportFrontImg(dto.getNicPassportFrontImg());
        }
        if (dto.getProfilePicture() != null) {
            user.setProfilePicture(dto.getProfilePicture());
        }
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setUsername(dto.getUsername());
        user.setRemarks(dto.getRemarks());
        user.setDob(dto.getDob());
        userRepo.save(user);
    }


    @Override
    public void delete(String userId) throws NotFoundException {
        if (userRepo.findById(userId).isEmpty()) {
            throw new NotFoundException("user : " + userId + " is not found");
        }
        userRepo.deleteById(userId);
    }

    @Override
    public UserDTO get(String userId) throws NotFoundException {
        Optional<User> byId = userRepo.findById(userId);
        if (byId.isEmpty()) {
            throw new NotFoundException("user : " + userId + " is not found");
        }
        return mapper.toUserDto(byId.get());
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepo.findAll().stream().map(mapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    public void login(String username, String password) {

    }

}
