package co.com.pragma.backend_challenge.user.application.mapper.request;

import co.com.pragma.backend_challenge.user.application.dto.request.OwnerRequest;
import co.com.pragma.backend_challenge.user.domain.model.User;
import co.com.pragma.backend_challenge.user.domain.util.annotation.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OwnerRequestMapper {
    User toDomain(OwnerRequest owner);
    List<User> toDomains(List<OwnerRequest> owners);
}
