package br.com.izilearn.izilearn_application.infrastructure.web.dto.profile.request;

import br.com.izilearn.izilearn_application.core.domain.enums.TypeProfile;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateProfileRequest {

    @NotNull(message = "Invalid id")
    private long id;

    @NotNull(message = "Type profile cannot be empty")
    private TypeProfile typeProfile;

}
