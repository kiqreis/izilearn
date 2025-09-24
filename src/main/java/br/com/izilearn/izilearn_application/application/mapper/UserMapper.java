package br.com.izilearn.izilearn_application.application.mapper;

import br.com.izilearn.izilearn_application.core.domain.model.User;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.request.CreateUserRequest;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.request.UpdateUserRequest;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "hashPassword", source = "password")
    @Mapping(target = "urlImage", expression = "java(defaultImage(request.getUrlImage()))")
    User toUser(CreateUserRequest request);

    UserResponse toCreateUserResponse(User user);

    @Mapping(target = "hashPassword", source = "password")
    void updateFromDto(UpdateUserRequest request, @MappingTarget User user);

    default String defaultImage(String urlImage) {
        return urlImage != null ? urlImage : "https://default-image.com/user.png";
    }

}
