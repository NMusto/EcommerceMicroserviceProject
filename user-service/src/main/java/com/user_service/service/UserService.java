package com.user_service.service;

import com.user_service.client.cart.dto.CartApiResponse;
import com.user_service.client.order.dto.OrderApiResponse;
import com.user_service.dto.UserRequest;
import com.user_service.dto.UserResponse;
import com.user_service.dto.UserUpdateRequest;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long userId);
    CartApiResponse getCartByUserId(Long userId);
    OrderApiResponse getOrderByUserId(Long userId);
    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(Long userid, UserUpdateRequest userUpdateRequest);
    String deleteUser(Long userId);
}
