package co.com.pragma.backend_challenge.user.application.mapper.request;

import co.com.pragma.backend_challenge.user.application.dto.request.UserRequest;
import co.com.pragma.backend_challenge.user.domain.model.User;
import co.com.pragma.backend_challenge.user.domain.util.annotation.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRequestMapper {
    User toDomain(UserRequest owner);
    List<User> toDomains(List<UserRequest> owners);
}
