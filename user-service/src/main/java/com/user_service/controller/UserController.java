package com.user_service.controller;

import com.user_service.client.cart.dto.CartApiResponse;
import com.user_service.client.order.dto.OrderApiResponse;
import com.user_service.dto.UserRequest;
import com.user_service.dto.UserResponse;
import com.user_service.dto.UserUpdateRequest;
import com.user_service.entity.User;
import com.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        UserResponse user = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<CartApiResponse> getCartByUserId(@PathVariable Long userId) {
        CartApiResponse cart = userService.getCartByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(cart);
    }

    @GetMapping("/order/{userId}")
    public ResponseEntity<List<OrderApiResponse>> getOrdersByUserId(@PathVariable Long userId) {
        List<OrderApiResponse> orders = userService.getOrdersByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse user = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId,
                                            @RequestBody UserUpdateRequest userUpdateRequest) {
        UserResponse user = userService.updateUser(userId, userUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        String response = userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
