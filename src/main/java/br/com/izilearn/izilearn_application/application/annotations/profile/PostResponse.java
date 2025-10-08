package br.com.izilearn.izilearn_application.application.annotations.profile;

import br.com.izilearn.izilearn_application.infrastructure.web.dto.profile.request.CreateProfileRequest;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.profile.response.ProfileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Operation(
        summary = "Create a new user profile",
        tags = {"Profile"},
        requestBody = @RequestBody(
                description = "Profile data to create",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = CreateProfileRequest.class)
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Profile created successfully",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ProfileResponse.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Invalid profile data",
                        content = @Content(
                                mediaType = "text/plain",
                                schema = @Schema(implementation = String.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "Internal server error",
                        content = @Content(
                                mediaType = "text/plain",
                                schema = @Schema(implementation = String.class)
                        )
                )
        }
)
public @interface PostResponse {
}
