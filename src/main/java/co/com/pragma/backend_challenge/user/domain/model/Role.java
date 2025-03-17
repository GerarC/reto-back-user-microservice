package co.com.pragma.backend_challenge.user.domain.model;

import co.com.pragma.backend_challenge.user.domain.util.annotation.Generated;
import co.com.pragma.backend_challenge.user.domain.util.enums.RoleName;

@Generated
public class Role {
    private Long id;
    private RoleName name;

    private Role(RoleBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public static RoleBuilder builder(){
        return new RoleBuilder();
    }

    public static class RoleBuilder{
        private Long id;
        private RoleName name;

        public RoleBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public RoleBuilder name(RoleName name) {
            this.name = name;
            return this;
        }

        public Role build(){
            return new Role(this);
        }
    }
}
