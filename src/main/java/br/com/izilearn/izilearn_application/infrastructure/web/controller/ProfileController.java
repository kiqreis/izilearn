package br.com.izilearn.izilearn_application.infrastructure.web.controller;

import br.com.izilearn.izilearn_application.application.usecase.profile.CreateProfile;
import br.com.izilearn.izilearn_application.application.usecase.profile.GetAllProfiles;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.profile.request.CreateProfileRequest;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.profile.response.ProfileResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/profile")
@Tag(name = "Profile", description = "Endpoints for managing profiles of the users")
public class ProfileController {

    private final CreateProfile createProfile;
    private final GetAllProfiles getAllProfiles;

    @PostMapping
    public ResponseEntity<ProfileResponse> save(@Valid @RequestBody CreateProfileRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createProfile.execute(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> getAllUserProfiles(@PathVariable("id") Long id) {
        return ResponseEntity.ok(getAllProfiles.execute(id));
    }

}
