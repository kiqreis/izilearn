package br.com.izilearn.izilearn_application.infrastructure.web.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateUserRequest {

    @Size(max = 120)
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Size(max = 180)
    @Email(message = "Invalid email")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Size(max = 60)
    @NotBlank(message = "Password cannot be empty")
    private String password;

    @Size(max = 11)
    @NotBlank(message = "Cellphone number cannot be empty")
    private String cellphoneNumber;

    @Size(max = 255)
    private String urlImage;

}
