package com.litter.litter.payload.response.user;

import com.litter.litter.model.Role;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class UpdateUserResponse {
    @NotNull
    private Long id;

    private String token;

    private String type = "Bearer";

    private String username;

    private String email;

    private Set<Role> roles;

    public UpdateUserResponse(Long id, String token, String type, String username, String email, Set<Role> roles) {
        this.id = id;
        this.token = token;
        this.type = type;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
