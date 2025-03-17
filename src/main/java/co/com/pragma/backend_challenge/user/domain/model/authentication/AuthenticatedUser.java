package co.com.pragma.backend_challenge.user.domain.model.authentication;


import co.com.pragma.backend_challenge.user.domain.util.annotation.Generated;
import co.com.pragma.backend_challenge.user.domain.util.enums.RoleName;

@Generated
public class AuthenticatedUser {
    private String token;
    private RoleName role;
    private String id;

    private AuthenticatedUser(AuthenticatedUserBuilder builder) {
        this.token = builder.token;
        this.role = builder.role;
        this.id = builder.id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RoleName getRole() {
        return role;
    }

    public void setRole(RoleName role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static AuthenticatedUserBuilder builder() {
        return new AuthenticatedUserBuilder();
    }

    public static class AuthenticatedUserBuilder {
        private String token;
        private RoleName role;
        private String id;

        public AuthenticatedUserBuilder token(String token) {
            this.token = token;
            return this;
        }

        public AuthenticatedUserBuilder role(RoleName role) {
            this.role = role;
            return this;
        }

        public AuthenticatedUserBuilder id(String id) {
            this.id = id;
            return this;
        }

        public AuthenticatedUser build() {
            return new AuthenticatedUser(this);
        }
    }
}