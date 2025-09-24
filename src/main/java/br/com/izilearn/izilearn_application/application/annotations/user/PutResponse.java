package br.com.izilearn.izilearn_application.application.annotations.user;

import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.request.UpdateUserRequest;
import br.com.izilearn.izilearn_application.infrastructure.web.dto.user.response.UserResponse;
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
        summary = "Update an user data",
        tags = {"User"},
        requestBody = @RequestBody(
                description = "User data to update",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = UpdateUserRequest.class)
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "User updated successfully",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = UserResponse.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Invalid user data",
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
public @interface PutResponse {
}
