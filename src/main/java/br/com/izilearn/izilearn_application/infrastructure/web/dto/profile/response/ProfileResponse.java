package br.com.izilearn.izilearn_application.infrastructure.web.dto.profile.response;

import br.com.izilearn.izilearn_application.core.domain.enums.TypeProfile;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProfileResponse {

    private String email;
    private List<TypeProfile> profiles;

}
