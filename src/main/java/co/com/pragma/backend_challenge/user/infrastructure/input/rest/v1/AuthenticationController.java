package co.com.pragma.backend_challenge.user.infrastructure.input.rest.v1;

import co.com.pragma.backend_challenge.user.application.dto.request.AuthenticationRequest;
import co.com.pragma.backend_challenge.user.application.dto.response.AuthenticationResponse;
import co.com.pragma.backend_challenge.user.application.handler.AuthenticationHandler;
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

    @GetMapping("/login")
    public final ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.accepted().body(
                authenticationHandler.login(request)
        );
    }

    @GetMapping("/token")
    public final ResponseEntity<AuthenticationResponse> validateToken(String token){
        return ResponseEntity.ok(
                authenticationHandler.validateToken(token)
        );
    }

}
