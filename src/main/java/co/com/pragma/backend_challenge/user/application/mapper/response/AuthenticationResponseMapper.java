package co.com.pragma.backend_challenge.user.application.mapper.response;

import co.com.pragma.backend_challenge.user.application.dto.response.AuthenticationResponse;
import co.com.pragma.backend_challenge.user.application.dto.response.UserResponse;
import co.com.pragma.backend_challenge.user.domain.model.User;
import co.com.pragma.backend_challenge.user.domain.model.authentication.AuthenticatedUser;
import co.com.pragma.backend_challenge.user.domain.util.annotation.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthenticationResponseMapper {
    AuthenticationResponse toResponse(AuthenticatedUser user);
    List<AuthenticationResponse> toResponse(List<AuthenticatedUser> users);
}
