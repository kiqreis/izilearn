package br.com.izilearn.izilearn_application.application.annotations.user;

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
        summary = "Delete an user",
        tags = {"User"},
        responses = {
                @ApiResponse(responseCode = "204", description = "Delete an user data"),
                @ApiResponse(
                        responseCode = "404",
                        description = "User not found",
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
public @interface DeleteResponse {
}
