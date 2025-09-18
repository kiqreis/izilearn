package br.com.izilearn.izilearn_application.application.mapper;

import br.com.izilearn.izilearn_application.core.domain.model.User;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.request.CreateUserRequest;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.response.CreateUserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(CreateUserRequest createUserRequest);

    CreateUserResponse toCreateUserResponse(User user);

}
