package co.com.pragma.backend_challenge.user.infrastructure.input.rest.v1;

import co.com.pragma.backend_challenge.user.application.dto.request.EmployeeRequest;
import co.com.pragma.backend_challenge.user.application.dto.request.UserRequest;
import co.com.pragma.backend_challenge.user.application.dto.response.IsOwnerResponse;
import co.com.pragma.backend_challenge.user.application.dto.response.UserResponse;
import co.com.pragma.backend_challenge.user.application.handler.UserHandler;
import co.com.pragma.backend_challenge.user.infrastructure.configuration.advisor.response.ExceptionResponse;
import co.com.pragma.backend_challenge.user.infrastructure.configuration.advisor.response.ValidationExceptionResponse;
import co.com.pragma.backend_challenge.user.infrastructure.util.constant.RestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CONFLICT,
                    description = RestConstants.SWAGGER_ERROR_USER_WITH_EMAIL_ALREADY_EXISTS,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CONFLICT,
                    description = RestConstants.SWAGGER_ERROR_USER_WITH_ID_ALREADY_EXISTS,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CONFLICT,
                    description = RestConstants.SWAGGER_ERROR_USER_UNDER_AGED,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),

            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_BAD_REQUEST,
                    description = RestConstants.SWAGGER_ERROR_VALIDATIONS_DO_NOT_PASS,
                    content =  @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))
            ),
    })
    @PostMapping("/owners")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserResponse> createOwner(@RequestBody @Valid UserRequest owner){
        return ResponseEntity.status(HttpStatus.CREATED).body(userHandler.createOwner(owner));
    }

    @PostMapping("/employees")
    public ResponseEntity<UserResponse> createOwner(@RequestBody @Valid EmployeeRequest employeeRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userHandler.createEmployee(employeeRequest));
    }

    @Operation(summary = RestConstants.SWAGGER_SUMMARY_GET_IS_OWNER)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_OK,
                    description = RestConstants.SWAGGER_DESCRIPTION_EXIST_USER,
                    content =  @Content(schema = @Schema(implementation = IsOwnerResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_ERROR_USER_DOES_NOT_EXIST,
                    content =  @Content(schema = @Schema(implementation = IsOwnerResponse.class))
            ),
    })
    @GetMapping("/{id}/is-owner")
    public ResponseEntity<IsOwnerResponse> isOwner(@PathVariable String id){
        return ResponseEntity.ok(
                userHandler.isOwner(id)
        );
    }


}
