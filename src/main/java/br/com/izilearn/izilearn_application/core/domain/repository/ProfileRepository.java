package br.com.izilearn.izilearn_application.core.domain.repository;

import br.com.izilearn.izilearn_application.core.domain.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
