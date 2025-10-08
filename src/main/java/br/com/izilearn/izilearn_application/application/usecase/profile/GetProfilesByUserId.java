package br.com.izilearn.izilearn_application.application.usecase.profile;

import br.com.izilearn.izilearn_application.application.mapper.ProfileMapper;
import br.com.izilearn.izilearn_application.core.domain.model.Profile;
import br.com.izilearn.izilearn_application.core.domain.repository.ProfileRepository;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.profile.response.ProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAllProfiles {

    private final ProfileRepository repository;
    private final ProfileMapper profileMapper;

    public ProfileResponse execute(Long id) {
        Profile profile = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        return profileMapper.toProfileResponse(profile);
    }

}
