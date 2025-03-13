package co.com.pragma.backend_challenge.user.infrastructure.output.jpa.mapper;

import co.com.pragma.backend_challenge.user.domain.model.Role;
import co.com.pragma.backend_challenge.user.domain.util.annotation.Generated;
import co.com.pragma.backend_challenge.user.infrastructure.output.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleEntityMapper {
    RoleEntity toEntity(Role role);
    List<RoleEntity> toEntities(List<Role> roles);
    Role toDomain(RoleEntity entity);
    List<Role> toDomains(List<RoleEntity> roleEntities);
}
