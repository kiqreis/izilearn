package br.com.izilearn.izilearn_application.infrastructure.web.controller;

import br.com.izilearn.izilearn_application.application.annotations.user.PostResponse;
import br.com.izilearn.izilearn_application.application.usecase.user.CreateUserUseCase;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.request.CreateUserRequest;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.response.CreateUserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    @PostResponse
    @PostMapping
    public ResponseEntity<CreateUserResponse> save(@Valid @RequestBody CreateUserRequest request) {
        CreateUserResponse response = createUserUseCase.execute(request);

        URI location = URI.create(String.format("/user/%s", response.getEmail()));

        return ResponseEntity.created(location).body(response);
    }

}
