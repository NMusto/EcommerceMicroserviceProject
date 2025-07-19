package com.user_service.service;

import com.user_service.client.cart.CartApi;
import com.user_service.client.cart.dto.CartApiRequest;
import com.user_service.client.cart.dto.CartApiResponse;
import com.user_service.dto.UserRequest;
import com.user_service.dto.UserResponse;
import com.user_service.dto.UserUpdateRequest;
import com.user_service.entity.User;
import com.user_service.exception.UserNotFoundException;
import com.user_service.mapper.UserMapper;
import com.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CartApi cartApi;


    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long userId) {
        User user = this.getUser(userId);
        return userMapper.toUserResponse(user);
    }

    @Override
    public CartApiResponse getCartByUserId(Long userId) {

    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User user = userMapper.toUser(userRequest);
        userRepository.save(user);

        CartApiRequest cartApiRequest = new CartApiRequest(user.getId());
        cartApi.createCart(cartApiRequest);

        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUser(Long userid, UserUpdateRequest userUpdateRequest) {
        User user = this.getUser(userid);

        if (userUpdateRequest.name() != null) {
            user.setName(userUpdateRequest.name());
        }
        if (userUpdateRequest.lastname() != null) {
            user.setLastname(userUpdateRequest.lastname());
        }
        if (userUpdateRequest.phone() != null) {
            user.setPhone(userUpdateRequest.phone());
        }
        if (userUpdateRequest.email() != null) {
            user.setEmail(userUpdateRequest.email());
        }
        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    @Override
    public String deleteUser(Long userId) {
        if ( !userRepository.existsById(userId) ) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }

        userRepository.deleteById(userId);
        return "User with ID " + userId + " was successfully deleted";
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }
}
