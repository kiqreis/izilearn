package br.com.izilearn.izilearn_application.application.usecase.user;

import br.com.izilearn.izilearn_application.application.mapper.UserMapper;
import br.com.izilearn.izilearn_application.core.domain.model.User;
import br.com.izilearn.izilearn_application.core.domain.repository.UserRepository;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.request.UpdateUserRequest;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.response.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateUserTest {

    @InjectMocks
    private UpdateUser updateUser;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Test
    @DisplayName("updateUser returns an UserResponse when successful")
    void updateUser_ReturnsUserResponse_WhenSuccessful() {
        UpdateUserRequest request = UpdateUserRequest.builder()
                .name("Jureminha")
                .email("jureminha@email.com")
                .build();

        User user = new User();

        user.setId(1L);
        user.setName("Jurandir");
        user.setEmail("jurandir@email.com");

        UserResponse expectedResponse = UserResponse.builder()
                .name("Jureminha")
                .email("jureminha@email.com")
                .build();

        given(userRepository.existsById(anyLong())).willReturn(true);

        given(userRepository.getReferenceById(anyLong()))
                .willReturn(user);

        willDoNothing().given(userMapper).updateFromDto(request, user);

        given(userMapper.toUserResponse(user))
                .willReturn(expectedResponse);

        UserResponse result = updateUser.execute(user.getId(), request);

        assertThat(result)
                .isNotNull()
                .extracting("name", "email")
                .containsExactly(expectedResponse.getName(), expectedResponse.getEmail());


    }

    @Test
    @DisplayName("updateUser throws an exception when user not found")
    void updateUser_ThrowsException_WhenUserNotFound() {
        UpdateUserRequest request = UpdateUserRequest.builder()
                .name("Jureminha")
                .email("jureminha@email.com")
                .build();

        given(userRepository.existsById(anyLong())).willReturn(false);

        assertThatThrownBy(() -> updateUser.execute(999L, request))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("User not found");
    }

}