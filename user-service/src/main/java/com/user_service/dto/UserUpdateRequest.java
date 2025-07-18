package com.user_service.dto;

public record UserUpdateRequest(
        String name,
        String lastname,
        String phone,
        String email
) {}
