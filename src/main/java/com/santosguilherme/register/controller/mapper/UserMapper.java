package com.santosguilherme.register.controller.mapper;

import com.santosguilherme.register.controller.request.UserRequest;
import com.santosguilherme.register.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toUser(UserRequest request);
}
