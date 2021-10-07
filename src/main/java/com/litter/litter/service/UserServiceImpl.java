package com.litter.litter.service;

import com.litter.litter.model.ERole;
import com.litter.litter.model.Role;
import com.litter.litter.model.User;
import com.litter.litter.payload.request.auth.RegisterRequest;
import com.litter.litter.payload.request.user.UpdateUserRequest;
import com.litter.litter.payload.response.MessageResponse;
import com.litter.litter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public User save(User user){
        return userRepository.save(user);
    };

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername((username));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public ResponseEntity validateFields(RegisterRequest registerRequest) {
        if(existsByEmail(registerRequest.getEmail()) & existsByUsername(registerRequest.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse("ERRO: Usuário e Email já existem"));
        }
        if (existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("ERRO: Nome de usuário já existe"));
        }
        if (existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("ERRO: Email já existe"));
        }

        return null;
    }

    @Override
    public ResponseEntity validateFields(UpdateUserRequest updateUserRequest) {
        User prevUser = findById(updateUserRequest.getId());

        if(
        (prevUser.getUsername() != updateUserRequest.getUsername() && prevUser.getEmail() != prevUser.getEmail())
        &
        (existsByEmail(updateUserRequest.getEmail()) & existsByUsername(updateUserRequest.getUsername()))){
            return ResponseEntity.badRequest().body(new MessageResponse("ERRO: Usuário e Email já existem"));
        }
        if (prevUser.getUsername() == updateUserRequest.getUsername() && existsByUsername(updateUserRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("ERRO: Nome de usuário já existe"));
        }
        if (prevUser.getEmail() == updateUserRequest.getEmail() && existsByEmail(updateUserRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("ERRO: Email já existe"));
        }

        return null;
    }

    @Override
    public User buildUserForSave(RegisterRequest registerRequest) {
        User user = new User(registerRequest.getUsername(), registerRequest.getEmail(), encoder.encode(registerRequest.getPassword()));

        user.setRoles(buildRoles(registerRequest.getRoles()));

        return user;
    }
    @Override
    public User buildUserForSave(UpdateUserRequest updateUserRequest) {
        User user = new User(updateUserRequest.getId(),updateUserRequest.getUsername(), updateUserRequest.getEmail(), encoder.encode(updateUserRequest.getPassword()));

        user.setRoles(buildRoles(updateUserRequest.getRoles()));

        return user;
    }

    @Override
    public Set<Role> buildRoles(Set<String> strRoles) {
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleService.findByName(ERole.ROLE_USER);
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleService.findByName(ERole.ROLE_ADMIN);
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleService.findByName(ERole.ROLE_MODERATOR);
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleService.findByName(ERole.ROLE_USER);
                        roles.add(userRole);
                }
            });
        }

        return roles;
    }
}
