package br.com.izilearn.izilearn_application.infrastructure.web.dto.profile.response;

import br.com.izilearn.izilearn_application.core.domain.enums.TypeProfile;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.response.UserResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProfileResponse {

    private TypeProfile typeProfile;
    private UserResponse userResponse;

}
