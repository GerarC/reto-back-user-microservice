package co.com.pragma.backend_challenge.user.infrastructure.output.jpa.mapper;

import co.com.pragma.backend_challenge.user.domain.model.User;
import co.com.pragma.backend_challenge.user.domain.util.annotation.Generated;
import co.com.pragma.backend_challenge.user.infrastructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {
    UserEntity toEntity(User user);
    List<UserEntity> toEntities(List<User> users);
    User toDomain(UserEntity entity);
    List<User> toDomains(List<UserEntity> entities);
}
