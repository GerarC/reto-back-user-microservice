package co.com.pragma.backend_challenge.user.domain.model;

import co.com.pragma.backend_challenge.user.domain.util.annotation.Generated;

import java.time.LocalDate;

@Generated
public class User {
    private String id;
    private String identityDocument;
    private String name;
    private String lastname;
    private LocalDate birthdate;
    private String phone;
    private String email;
    private String password;
    private Role role;

    private User(UserBuilder builder) {
        this.id = builder.id;
        this.identityDocument = builder.identityDocument;
        this.name = builder.name;
        this.lastname = builder.lastname;
        this.birthdate = builder.birthdate;
        this.phone = builder.phone;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentityDocument() {
        return identityDocument;
    }

    public void setIdentityDocument(String identityDocument) {
        this.identityDocument = identityDocument;
    }

    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public static class UserBuilder {
        private String id;
        private String identityDocument;
        private String name;
        private String lastname;
        private LocalDate birthdate;
        private String phone;
        private String email;
        private String password;
        private Role role;

        public UserBuilder id(String id) {
            this.id = id;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder identityDocument(String identityDocument) {
            this.identityDocument = identityDocument;
            return this;
        }

        public UserBuilder birthdate(LocalDate birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public UserBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}
