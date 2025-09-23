package br.com.izilearn.izilearn_application.application.usecase.user;

import br.com.izilearn.izilearn_application.application.mapper.UserMapper;
import br.com.izilearn.izilearn_application.core.domain.model.User;
import br.com.izilearn.izilearn_application.core.domain.repository.UserRepository;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.response.CreateUserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUserByIdUseCase {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Transactional
    public CreateUserResponse execute(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapper.toCreateUserResponse(user);
    }

}
