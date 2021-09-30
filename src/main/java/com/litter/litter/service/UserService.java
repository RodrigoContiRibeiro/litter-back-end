package com.litter.litter.service;

import com.litter.litter.model.User;

public interface UserService {
    public User save(User user);

    public User findByUsername(String username);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);
}
