package br.com.izilearn.izilearn_application.application.usecase.profile;

import br.com.izilearn.izilearn_application.application.mapper.ProfileMapper;
import br.com.izilearn.izilearn_application.application.usecase.profile.exception.ProfileNotFoundException;
import br.com.izilearn.izilearn_application.core.domain.enums.TypeProfile;
import br.com.izilearn.izilearn_application.core.domain.model.Profile;
import br.com.izilearn.izilearn_application.core.domain.repository.ProfileRepository;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.profile.response.ProfileResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GetProfilesByUserIdTest {

    @InjectMocks
    private GetProfilesByUserId useCase;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private ProfileMapper profileMapper;

    @Test
    @DisplayName("getProfilesByUserId returns ProfileResponse when successful")
    void getProfilesByUserId_ReturnsProfileResponse_WhenSuccessful() {
        ProfileResponse expectedResponse = ProfileResponse.builder()
                .email("jurandir@email.com")
                .profiles(List.of(TypeProfile.STUDENT, TypeProfile.TEACHER))
                .build();

        given(profileRepository.findById(anyLong()))
                .willReturn(Optional.of(new Profile()));

        given(profileMapper.toProfileResponse(any(Profile.class)))
                .willReturn(expectedResponse);

        ProfileResponse result = useCase.execute(1L);

        assertThat(result)
                .isNotNull()
                .extracting("email", "profiles")
                .containsExactly(expectedResponse.getEmail(), expectedResponse.getProfiles());
    }

    @Test
    @DisplayName("getProfilesByUserId throws exception when profile not found")
    void getProfilesByUserId_ThrowsException_WhenProfileNotFound() {
        given(profileRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(999L))
                .isInstanceOf(ProfileNotFoundException.class)
                .hasMessage("Profile not found");
    }

}