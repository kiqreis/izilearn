package br.com.izilearn.izilearn_application.core.domain.repository;

import br.com.izilearn.izilearn_application.core.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
