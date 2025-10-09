package br.com.izilearn.izilearn_application.application.usecase.profile;

import br.com.izilearn.izilearn_application.application.mapper.ProfileMapper;
import br.com.izilearn.izilearn_application.application.usecase.profile.exception.ProfileAlreadyExistsException;
import br.com.izilearn.izilearn_application.application.usecase.user.exception.UserNotFoundException;
import br.com.izilearn.izilearn_application.core.domain.enums.TypeProfile;
import br.com.izilearn.izilearn_application.core.domain.model.Profile;
import br.com.izilearn.izilearn_application.core.domain.model.User;
import br.com.izilearn.izilearn_application.core.domain.repository.ProfileRepository;
import br.com.izilearn.izilearn_application.core.domain.repository.UserRepository;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.profile.request.CreateProfileRequest;
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
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class CreateProfileTest {

    @InjectMocks
    private CreateProfile useCase;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private ProfileMapper profileMapper;

    @Test
    @DisplayName("createProfile returns a ProfileResponse when successful")
    void createProfile_ReturnsProfileResponse_WhenSuccessful() {
        Long id = 1L;

        CreateProfileRequest request = CreateProfileRequest.builder()
                .id(id)
                .typeProfile(TypeProfile.STUDENT)
                .build();

        User user = new User();
        user.setId(id);
        user.setName("Jurandir");
        user.setEmail("jurandir@email.com");

        Profile profile = new Profile();
        profile.setId(id);
        profile.setTypeProfile(TypeProfile.STUDENT);
        profile.setUser(user);

        ProfileResponse expectedResponse = ProfileResponse.builder()
                .email("jurandir@email.com")
                .profiles(List.of(TypeProfile.STUDENT))
                .build();

        given(userRepository.findById(anyLong()))
                .willReturn(Optional.of(user));

        given(profileMapper.toProfile(request))
                .willReturn(profile);

        given(profileRepository.save(any(Profile.class)))
                .willAnswer(i -> i.getArgument(0));

        given(profileMapper.toProfileResponse(profile))
                .willReturn(expectedResponse);

        ProfileResponse response = useCase.execute(request);

        assertThat(response)
                .isNotNull()
                .extracting("email", "profiles")
                .containsExactly(expectedResponse.getEmail(), expectedResponse.getProfiles());
    }

    @Test
    @DisplayName("createProfile throws UserNotFoundException when user not found")
    void createProfile_ThrowsUserNotFoundException_WhenUserNotFound() {
        CreateProfileRequest request = CreateProfileRequest.builder()
                .id(999L)
                .typeProfile(TypeProfile.TEACHER)
                .build();

        given(userRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(request))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User not found by id");
    }

    @Test
    @DisplayName("createProfile throws ProfileAlreadyExistsException when profile already exists for user")
    void createProfile_ThrowsProfileAlreadyExistsException_WhenProfileAlreadyExists() {
        Long id = 1L;

        CreateProfileRequest request = CreateProfileRequest.builder()
                .id(id)
                .typeProfile(TypeProfile.STUDENT)
                .build();

        User user = new User();
        user.setId(id);
        user.setName("Jurandir");
        user.setEmail("jurandir@email.com");

        Profile profile = new Profile();
        profile.setId(1L);
        profile.setTypeProfile(TypeProfile.STUDENT);
        profile.setUser(user);

        user.setProfiles(List.of(profile));

        given(userRepository.findById(id))
                .willReturn(Optional.of(user));

        assertThatThrownBy(() -> useCase.execute(request))
                .isInstanceOf(ProfileAlreadyExistsException.class)
                .hasMessage("User already contains a profile this type");
    }

}