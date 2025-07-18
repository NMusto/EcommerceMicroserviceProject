package com.user_service.mapper;

import com.user_service.dto.UserResponse;
import com.user_service.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);
}
