package br.com.izilearn.izilearn_application.infrastructure.web.controller;

import br.com.izilearn.izilearn_application.application.usecase.profile.CreateProfile;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.profile.request.CreateProfileRequest;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.profile.response.ProfileResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/profile")
@Tag(name = "Profile", description = "Endpoints for managing profiles of the users")
public class ProfileController {

    private final CreateProfile createProfile;

    @PostMapping
    public ResponseEntity<ProfileResponse> save(@Valid @RequestBody CreateProfileRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createProfile.execute(request));
    }

}
