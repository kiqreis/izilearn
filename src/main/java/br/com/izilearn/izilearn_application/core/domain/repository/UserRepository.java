package br.com.izilearn.izilearn_application.core.domain.repository;

import br.com.izilearn.izilearn_application.core.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
