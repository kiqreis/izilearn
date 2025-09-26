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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ListUsersTest {

    @InjectMocks
    private ListUsers useCase;

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @Test
    @DisplayName("listUsers returns paginated users when successful")
    void listUsers_ReturnsPaginatedUsers_WhenSuccessful() {
        Pageable pageable = PageRequest.of(0, 10);

        User userOne = new User();
        userOne.setId(1L);
        userOne.setName("Jurandir");
        userOne.setEmail("jurandir@email.com");

        User userTwo = new User();
        userTwo.setId(2L);
        userTwo.setName("Jureminha");
        userTwo.setEmail("jureminha@email.com");

        UserResponse responseOne = UserResponse.builder()
                .name("Jurandir")
                .email("jurandir@email.com")
                .build();

        UserResponse responseTwo = UserResponse.builder()
                .name("Jureminha")
                .email("jureminha@email.com")
                .build();

        Page<User> userPage = new PageImpl<>(List.of(userOne, userTwo), pageable, 2);

        given(repository.findAll(pageable)).willReturn(userPage);

        given(mapper.toUserResponse(userOne)).willReturn(responseOne);

        given(mapper.toUserResponse(userTwo)).willReturn(responseTwo);

        Page<UserResponse> result = useCase.execute(pageable);

        assertThat(result).isNotNull();

        assertThat(result.getContent())
                .hasSize(2)
                .containsExactly(responseOne, responseTwo);

        assertThat(result.getTotalElements()).isEqualTo(2);

        assertThat(result.getPageable().getPageNumber())
                .isEqualTo(0);

        assertThat(result.getPageable().getPageSize())
                .isEqualTo(10);
    }

}