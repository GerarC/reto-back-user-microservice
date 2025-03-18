package co.com.pragma.backend_challenge.user.infrastructure.input.rest.v1;

import co.com.pragma.backend_challenge.user.application.dto.request.AuthenticationRequest;
import co.com.pragma.backend_challenge.user.application.dto.response.AuthenticationResponse;
import co.com.pragma.backend_challenge.user.application.handler.AuthenticationHandler;
import co.com.pragma.backend_challenge.user.infrastructure.configuration.advisor.response.ExceptionResponse;
import co.com.pragma.backend_challenge.user.infrastructure.configuration.advisor.response.ValidationExceptionResponse;
import co.com.pragma.backend_challenge.user.infrastructure.util.constant.RestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthenticationController {
    private final AuthenticationHandler authenticationHandler;


    @Operation(summary = RestConstants.SWAGGER_SUMMARY_LOGIN)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_ACCEPTED,
                    description = RestConstants.SWAGGER_DESCRIPTION_LOGGED_IN,
                    content =  @Content(schema = @Schema(implementation = AuthenticationResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CONFLICT,
                    description = RestConstants.SWAGGER_ERROR_WRONG_CREDENTIALS,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_BAD_REQUEST,
                    description = RestConstants.SWAGGER_ERROR_VALIDATIONS_DO_NOT_PASS,
                    content =  @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))
            ),
    })
    @PermitAll
    @GetMapping("/login")
    public final ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.accepted().body(
                authenticationHandler.login(request)
        );
    }

    @Operation(summary = RestConstants.SWAGGER_SUMMARY_AUTHORIZE)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_OK,
                    description = RestConstants.SWAGGER_DESCRIPTION_AUTHORIZED,
                    content =  @Content(schema = @Schema(implementation = AuthenticationResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CONFLICT,
                    description = RestConstants.SWAGGER_ERROR_TOKEN_UNAUTHORIZED,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_BAD_REQUEST,
                    description = RestConstants.SWAGGER_ERROR_VALIDATIONS_DO_NOT_PASS,
                    content =  @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))
            ),
    })
    @PermitAll
    @GetMapping("/authorize")
    public final ResponseEntity<AuthenticationResponse> validateToken(String token){
        return ResponseEntity.ok(
                authenticationHandler.validateToken(token)
        );
    }

}
