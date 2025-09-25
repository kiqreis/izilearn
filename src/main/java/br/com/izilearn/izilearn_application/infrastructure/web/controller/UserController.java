package br.com.izilearn.izilearn_application.infrastructure.web.controller;

import br.com.izilearn.izilearn_application.application.annotations.user.GetPageResponse;
import br.com.izilearn.izilearn_application.application.annotations.user.GetResponse;
import br.com.izilearn.izilearn_application.application.annotations.user.PostResponse;
import br.com.izilearn.izilearn_application.application.annotations.user.PutResponse;
import br.com.izilearn.izilearn_application.application.usecase.user.CreateUser;
import br.com.izilearn.izilearn_application.application.usecase.user.GetUserById;
import br.com.izilearn.izilearn_application.application.usecase.user.ListUsers;
import br.com.izilearn.izilearn_application.application.usecase.user.UpdateUser;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.request.CreateUserRequest;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.request.UpdateUserRequest;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.response.UserResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.data.domain.Sort.Direction.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "Endpoints for managing users")
public class UserController {

    private final CreateUser createUser;
    private final GetUserById getUserById;
    private final UpdateUser updateUser;
    private final ListUsers listUsers;

    @PostResponse
    @PostMapping
    public ResponseEntity<UserResponse> save(@Valid @RequestBody CreateUserRequest request) {
        UserResponse response = createUser.execute(request);

        URI location = URI.create(String.format("/user/%s", response.getEmail()));

        return ResponseEntity.created(location).body(response);
    }

    @GetResponse
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(getUserById.execute(id));
    }

    @GetPageResponse
    @GetMapping
    public ResponseEntity<Page<UserResponse>> listUsers(
            @PageableDefault(size = 5, sort = "name", direction = ASC) Pageable pageable) {
        return ResponseEntity.ok(listUsers.execute(pageable));
    }

    @PutResponse
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable("id") Long id, @Valid @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(updateUser.execute(id, request));
    }

}
