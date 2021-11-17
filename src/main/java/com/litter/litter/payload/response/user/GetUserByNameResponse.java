package com.litter.litter.payload.response.user;

import com.litter.litter.model.Role;

import java.util.Set;

public class GetUserByNameResponse extends GetUserResponse{
    public GetUserByNameResponse() {
        super();
    }

    public GetUserByNameResponse(Long id, String username, String email, Set<Role> roles) {
        super(id, username, email, roles);
    }
}
