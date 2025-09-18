package br.com.izilearn.izilearn_application.infrastructure.web.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserResponse {

    private String name;
    private String email;

}
