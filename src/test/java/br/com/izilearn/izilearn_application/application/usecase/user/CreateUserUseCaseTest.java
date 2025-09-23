package br.com.izilearn.izilearn_application.application.usecase.user;

import br.com.izilearn.izilearn_application.application.mapper.UserMapper;
import br.com.izilearn.izilearn_application.core.domain.model.User;
import br.com.izilearn.izilearn_application.core.domain.repository.UserRepository;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.request.CreateUserRequest;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.response.CreateUserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseTest {

    @InjectMocks
    private CreateUserUseCase useCase;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Test
    @DisplayName("createUser returns a CreateUserResponse when successful")
    void createUser_ReturnsCreateUserResponse_WhenSuccessful() {
        CreateUserRequest request = CreateUserRequest.builder()
                .name("Jurandir")
                .email("jurandir@email.com")
                .password("Jur@ndir123")
                .cellphoneNumber("11999999999")
                .build();

        User user = new User();

        user.setId(1L);
        user.setName("Jurandir");
        user.setEmail("jurandir@email.com");

        CreateUserResponse expectedResponse = CreateUserResponse.builder()
                .name("Jurandir")
                .email("jurandir@email.com")
                .build();

        when(userMapper.toUser(request))
                .thenReturn(user);

        when(userRepository.save(any(User.class)))
                .thenAnswer(i -> i.getArgument(0));

        when(userMapper.toCreateUserResponse(user))
                .thenReturn(expectedResponse);

        CreateUserResponse result = useCase.execute(request);

        assertThat(result)
                .isNotNull()
                .extracting("name", "email")
                .containsExactly(expectedResponse.getName(), expectedResponse.getEmail());
    }

}