package br.com.izilearn.izilearn_application.application.usecase.user;

import br.com.izilearn.izilearn_application.core.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteUserTest {

    @InjectMocks
    private DeleteUser useCase;

    @Mock
    private UserRepository repository;

    @Test
    @DisplayName("deleteUser returns void when successful")
    void deleteUser_ReturnsVoid_WhenSuccessful() {
        given(repository.existsById(anyLong())).willReturn(true);

        willDoNothing().given(repository)
                .deleteById(anyLong());

        assertThatNoException()
                .isThrownBy(() -> useCase.execute(anyLong()));
    }

    @Test
    @DisplayName("deleteUser throws exception when user not found")
    void deleteUser_ThrowsException_WhenUserNotFound() {
        given(repository.existsById(anyLong())).willReturn(false);

        assertThatThrownBy(() -> useCase.execute(anyLong()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("User not found");
    }

}