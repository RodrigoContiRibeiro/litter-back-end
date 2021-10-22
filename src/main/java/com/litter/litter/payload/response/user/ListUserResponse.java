package com.litter.litter.payload.response.user;

import com.litter.litter.model.User;

import java.util.List;

public class ListUserResponse {
    List<User> users;

    public ListUserResponse(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
