package br.com.izilearn.izilearn_application.application.mapper;

import br.com.izilearn.izilearn_application.core.domain.enums.TypeProfile;
import br.com.izilearn.izilearn_application.core.domain.model.Profile;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.profile.request.CreateProfileRequest;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.profile.response.ProfileResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(target = "id", ignore = true)
    Profile toProfile(CreateProfileRequest request);

    @Mapping(target = "email", expression = "java(profile.getUser().getEmail())")
    @Mapping(target = "profiles", expression = "java(getTypeProfiles(profile))")
    ProfileResponse toProfileResponse(Profile profile);

    void updateFromDto(CreateProfileRequest request, @MappingTarget Profile profile);

    default List<TypeProfile> getTypeProfiles(Profile profile) {
        return profile.getUser().getProfiles()
                .stream()
                .map(Profile::getTypeProfile)
                .toList();
    }

}
