package br.com.izilearn.izilearn_application.application.usecase.profile;

import br.com.izilearn.izilearn_application.application.mapper.ProfileMapper;
import br.com.izilearn.izilearn_application.core.domain.enums.TypeProfile;
import br.com.izilearn.izilearn_application.core.domain.model.Profile;
import br.com.izilearn.izilearn_application.core.domain.model.User;
import br.com.izilearn.izilearn_application.core.domain.repository.ProfileRepository;
import br.com.izilearn.izilearn_application.core.domain.repository.UserRepository;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.profile.request.CreateProfileRequest;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.profile.response.ProfileResponse;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.response.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
        Long userId = 1L;

        CreateProfileRequest request = CreateProfileRequest.builder()
                .id(userId)
                .typeProfile(TypeProfile.STUDENT)
                .build();


        User user = new User();
        user.setId(userId);
        user.setName("Jurandir");
        user.setEmail("jurandir@email.com");

        Profile profile = new Profile();
        profile.setId(userId);
        profile.setTypeProfile(TypeProfile.STUDENT);
        profile.setUser(user);

        UserResponse userResponse = UserResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();

        ProfileResponse expectedResponse = ProfileResponse.builder()
                .typeProfile(TypeProfile.STUDENT)
                .userResponse(userResponse)
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
                .extracting("typeProfile", "userResponse")
                .containsExactly(expectedResponse.getTypeProfile(), expectedResponse.getUserResponse());
    }

}