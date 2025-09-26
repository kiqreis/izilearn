package br.com.izilearn.izilearn_application.application.usecase.user;

import br.com.izilearn.izilearn_application.core.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteUser {

    private final UserRepository repository;

    @Transactional
    public void execute(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        repository.deleteById(id);
    }

}
