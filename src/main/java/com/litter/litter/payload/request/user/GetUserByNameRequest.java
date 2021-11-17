package com.litter.litter.payload.request.user;

import javax.validation.constraints.NotNull;

public class GetUserByNameRequest {
    @NotNull
    private String username;

    public GetUserByNameRequest() {
    }

    public GetUserByNameRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
