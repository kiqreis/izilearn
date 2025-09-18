package br.com.izilearn.izilearn_application.infrastructure.web.dto.user.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserResponse {

    private String name;
    private String email;

}
