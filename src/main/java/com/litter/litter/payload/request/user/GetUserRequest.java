package com.litter.litter.payload.request.user;

import javax.validation.constraints.NotNull;

public class GetUserRequest {
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
