package br.com.izilearn.izilearn_application.application.usecase.profile;

import br.com.izilearn.izilearn_application.application.mapper.ProfileMapper;
import br.com.izilearn.izilearn_application.application.usecase.user.exception.UserNotFoundException;
import br.com.izilearn.izilearn_application.core.domain.enums.TypeProfile;
import br.com.izilearn.izilearn_application.core.domain.model.Profile;
import br.com.izilearn.izilearn_application.core.domain.model.User;
import br.com.izilearn.izilearn_application.core.domain.repository.ProfileRepository;
import br.com.izilearn.izilearn_application.core.domain.repository.UserRepository;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.profile.request.CreateProfileRequest;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.profile.response.ProfileResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateProfile {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ProfileMapper mapper;


    @Transactional
    public ProfileResponse execute(CreateProfileRequest request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found by id"));

        List<TypeProfile> typeProfiles = user.getProfiles().stream()
                .map(Profile::getTypeProfile)
                .toList();

        if (typeProfiles.contains(request.getTypeProfile())) {
            throw new RuntimeException("User already contains a profile this type");
        }

        Profile profile = mapper.toProfile(request);
        profile.setUser(user);
        user.getProfiles().add(profile);

        return mapper.toProfileResponse(profileRepository.save(profile));
    }

}
