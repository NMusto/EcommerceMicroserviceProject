package com.user_service.service;

import com.user_service.dto.UserRequest;
import com.user_service.dto.UserResponse;
import com.user_service.dto.UserUpdateRequest;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long userId);
    // getCartByUserId(Long userId);
    // getOrdersByUserId(Long userId);
    UserResponse createUser(UserRequest userRequest);
    String updateUser(Long userid, UserUpdateRequest userUpdateRequest);
    String deleteUser(Long userId);
}
