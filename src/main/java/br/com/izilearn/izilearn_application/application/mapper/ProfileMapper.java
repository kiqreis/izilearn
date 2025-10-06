package br.com.izilearn.izilearn_application.application.mapper;

import br.com.izilearn.izilearn_application.core.domain.model.Profile;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.profile.request.CreateProfileRequest;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.profile.response.ProfileResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(target = "id", ignore = true)
    Profile toProfile(CreateProfileRequest request);

    @Mapping(target = "userResponse", source = "user")
    ProfileResponse toProfileResponse(Profile profile);

    void updateFromDto(CreateProfileRequest request, @MappingTarget Profile profile);

}
