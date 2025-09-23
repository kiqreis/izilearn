package br.com.izilearn.izilearn_application.application.usecase.user;

import br.com.izilearn.izilearn_application.application.mapper.UserMapper;
import br.com.izilearn.izilearn_application.core.domain.model.User;
import br.com.izilearn.izilearn_application.core.domain.repository.UserRepository;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.response.CreateUserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserByIdUseCaseTest {

    @InjectMocks
    private GetUserByIdUseCase useCase;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Test
    @DisplayName("getUserById returns a CreateUseResponse when successful")
    void getUserById_ReturnsCreateUserResponse_WhenSuccessful() {
        CreateUserResponse expectedResponse = CreateUserResponse.builder()
                .name("Jurandir")
                .email("jurandir@email.com")
                .build();

        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(new User()));

        when(userMapper.toCreateUserResponse(any(User.class)))
                .thenReturn(expectedResponse);

        CreateUserResponse result = useCase.execute(1L);

        assertThat(result)
                .isNotNull()
                .extracting("name", "email")
                .containsExactly("Jurandir", "jurandir@email.com");
    }

}