package br.com.izilearn.izilearn_application.application.usecase.user;

import br.com.izilearn.izilearn_application.application.mapper.UserMapper;
import br.com.izilearn.izilearn_application.core.domain.model.User;
import br.com.izilearn.izilearn_application.core.domain.repository.UserRepository;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.response.UserResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class GetUserByEmailTest {

    @InjectMocks
    private GetUserByEmail useCase;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Test
    @DisplayName("getUserByEmail returns an UserResponse when successful")
    void getUserByEmail_ReturnsUserResponse_WhenSuccessful() {
        UserResponse expectedResponse = UserResponse.builder()
                .name("Jurandir")
                .email("jurandir@email.com")
                .build();

        given(userRepository.findByEmail(anyString()))
                .willReturn(Optional.of(new User()));

        given(userMapper.toUserResponse(any(User.class)))
                .willReturn(expectedResponse);

        UserResponse result = useCase.execute("jurandir@email.com");

        assertThat(result)
                .isNotNull()
                .extracting("name", "email")
                .containsExactly(expectedResponse.getName(), expectedResponse.getEmail());

    }

}