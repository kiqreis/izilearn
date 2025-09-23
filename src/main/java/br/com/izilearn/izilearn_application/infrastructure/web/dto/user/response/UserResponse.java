package br.com.izilearn.izilearn_application.infrastructure.web.dto.user.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {

    private String name;
    private String email;

}
