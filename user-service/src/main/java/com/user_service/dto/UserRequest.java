package com.user_service.dto;

import jakarta.validation.constraints.NotNull;

public record UserRequest(

        @NotNull(message = "Name is required")
        String name,

        @NotNull(message = "Lastname is required")
        String lastname,

        @NotNull(message = "Phone is required")
        String phone,

        @NotNull(message = "Email is required")
        String email
) {}
