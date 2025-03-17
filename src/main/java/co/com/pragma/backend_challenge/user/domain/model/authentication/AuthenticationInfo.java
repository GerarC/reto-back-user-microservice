package co.com.pragma.backend_challenge.user.domain.model.authentication;

import co.com.pragma.backend_challenge.user.domain.util.annotation.Generated;

@Generated
public class AuthenticationInfo {
    private String id;
    private String password;

    private AuthenticationInfo(AuthenticationInfoBuilder builder) {
        this.id = builder.id;
        this.password = builder.password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static AuthenticationInfoBuilder builder() {
        return new AuthenticationInfoBuilder();
    }

    public static class AuthenticationInfoBuilder {
        private String id;
        private String password;

        public AuthenticationInfoBuilder id(String id) {
            this.id = id;
            return this;
        }

        public AuthenticationInfoBuilder password(String password) {
            this.password = password;
            return this;
        }

        public AuthenticationInfo build() {
            return new AuthenticationInfo(this);
        }
    }
}
