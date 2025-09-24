package br.com.izilearn.izilearn_application.infrastructure.web.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateUserRequest {

    @Size(max = 120)
    private String name;

    @Size(max = 180)
    @Email(message = "Invalid email")
    private String email;

    @Size(max = 60)
    private String password;

    @Size(max = 11)
    private String cellphoneNumber;

    @Size(max = 255)
    private String urlImage;

}
