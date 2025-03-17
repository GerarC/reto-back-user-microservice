package co.com.pragma.backend_challenge.user.infrastructure.output.jpa.entity;

import co.com.pragma.backend_challenge.user.domain.util.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private RoleName name;
}
