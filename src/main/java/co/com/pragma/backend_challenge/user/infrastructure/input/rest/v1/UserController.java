package co.com.pragma.backend_challenge.user.infrastructure.input.rest.v1;

import co.com.pragma.backend_challenge.user.application.dto.request.OwnerRequest;
import co.com.pragma.backend_challenge.user.application.dto.response.UserResponse;
import co.com.pragma.backend_challenge.user.application.handler.UserHandler;
import co.com.pragma.backend_challenge.user.infrastructure.util.constant.RestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserHandler userHandler;

    @Operation(summary = RestConstants.SWAGGER_SUMMARY_CREATE_OWNER)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CREATED,
                    description = RestConstants.SWAGGER_DESCRIPTION_CREATED_OWNER,
                    content =  @Content(schema = @Schema(implementation = UserResponse.class))
            )
    })
    @PostMapping("/owners")
    public ResponseEntity<UserResponse> createOwner(@RequestBody @Valid OwnerRequest owner){
        return ResponseEntity.ok(userHandler.createOwner(owner));
    }
}
