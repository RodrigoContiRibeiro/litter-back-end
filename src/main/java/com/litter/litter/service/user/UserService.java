package com.litter.litter.service.user;

import com.litter.litter.model.Role;
import com.litter.litter.model.User;
import com.litter.litter.payload.request.auth.RegisterRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface UserService {
    public User save(User user);

    public List<User> findAll();

    public User findById(Long id);

    public User findByUsername(String username);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);

    public ResponseEntity validateFields(RegisterRequest registerRequest);

    public User buildUserForSave(RegisterRequest registerRequest);

    public Set<Role> buildRoles(Set<String> strRoles);
}
