package com.user_service.service;

import com.user_service.dto.UserRequest;
import com.user_service.dto.UserResponse;
import com.user_service.dto.UserUpdateRequest;
import com.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;


    @Override
    public List<UserResponse> getAllUsers() {
        return null;
    }

    @Override
    public UserResponse getUserById(Long userId) {
        return null;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        return null;
    }

    @Override
    public String updateUser(Long userid, UserUpdateRequest userUpdateRequest) {
        return null;
    }

    @Override
    public String deleteUser(Long userId) {
        return null;
    }
}
