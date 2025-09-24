package br.com.izilearn.izilearn_application.application.usecase.user;

import br.com.izilearn.izilearn_application.application.mapper.UserMapper;
import br.com.izilearn.izilearn_application.core.domain.model.User;
import br.com.izilearn.izilearn_application.core.domain.repository.UserRepository;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.response.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserByIdTest {

    @InjectMocks
    private GetUserById useCase;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Test
    @DisplayName("getUserById returns a CreateUseResponse when successful")
    void getUserById_ReturnsCreateUserResponse_WhenSuccessful() {
        UserResponse expectedResponse = UserResponse.builder()
                .name("Jurandir")
                .email("jurandir@email.com")
                .build();

        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(new User()));

        when(userMapper.toCreateUserResponse(any(User.class)))
                .thenReturn(expectedResponse);

        UserResponse result = useCase.execute(1L);

        assertThat(result)
                .isNotNull()
                .extracting("name", "email")
                .containsExactly(expectedResponse.getName(), expectedResponse.getEmail());
    }

    @Test
    @DisplayName("getUserById throws exception when user not found")
    void getUserById_ThrowsException_WhenUserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(999L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("User not found");
    }

}