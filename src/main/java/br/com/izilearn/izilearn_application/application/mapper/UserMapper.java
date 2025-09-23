package br.com.izilearn.izilearn_application.application.mapper;

import br.com.izilearn.izilearn_application.core.domain.model.User;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.request.CreateUserRequest;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "hashPassword", source = "password")
    @Mapping(target = "urlImage", expression = "java(defaultImage(createUserRequest.getUrlImage()))")
    User toUser(CreateUserRequest createUserRequest);

    UserResponse toCreateUserResponse(User user);

    void updateFromDto(CreateUserRequest createUserRequest, @MappingTarget User user);

    default String defaultImage(String urlImage) {
        return urlImage != null ? urlImage : "https://default-image.com/user.png";
    }

}
