package br.com.izilearn.izilearn_application.infrastructure.web.controller;

import br.com.izilearn.izilearn_application.application.annotations.user.PostResponse;
import br.com.izilearn.izilearn_application.application.usecase.user.CreateUserUseCase;
import br.com.izilearn.izilearn_application.application.usecase.user.GetUserByIdUseCase;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.request.CreateUserRequest;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.response.CreateUserResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "Endpoints for managing users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;

    @PostResponse
    @PostMapping
    public ResponseEntity<CreateUserResponse> save(@Valid @RequestBody CreateUserRequest request) {
        CreateUserResponse response = createUserUseCase.execute(request);

        URI location = URI.create(String.format("/user/%s", response.getEmail()));

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateUserResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(getUserByIdUseCase.execute(id));
    }

}
