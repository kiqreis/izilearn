package br.com.izilearn.izilearn_application.application.annotations.profile;

import br.com.izilearn.izilearn_application.infrastructure.web.dto.profile.response.ProfileResponse;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Operation(
        summary = "Get a profile by user id",
        tags = {"Profile"},
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Return an user profile data",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ProfileResponse.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "User profile not found",
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
public @interface GetResponse {
}
