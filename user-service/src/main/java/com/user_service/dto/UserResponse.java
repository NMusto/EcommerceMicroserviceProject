package com.user_service.dto;

public record UserResponse(
        Long id,
        String name,
        String lastname,
        String phone,
        String email
) {}
