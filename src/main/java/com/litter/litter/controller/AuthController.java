package com.litter.litter.controller;

import com.litter.litter.model.ERole;
import com.litter.litter.model.Role;
import com.litter.litter.model.User;
import com.litter.litter.payload.request.LoginRequest;
import com.litter.litter.payload.request.RegisterRequest;
import com.litter.litter.payload.response.JwtResponse;
import com.litter.litter.payload.response.MessageResponse;
import com.litter.litter.security.jwt.JwtUtils;
import com.litter.litter.security.userDetails.UserDetailsImpl;
import com.litter.litter.service.RoleServiceImpl;
import com.litter.litter.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if(userService.existsByEmail(registerRequest.getEmail()) & userService.existsByUsername(registerRequest.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse("ERRO: Usuário e Email já existem"));
        }

        if (userService.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("ERRO: Nome de usuário já existe"));
        }

        if (userService.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("ERRO: Email já existe"));
        }

        User user = new User(registerRequest.getUsername(), registerRequest.getEmail(), encoder.encode(registerRequest.getPassword()));

        Set<String> strRoles = registerRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleService.findByName(ERole.USER);
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleService.findByName(ERole.ADMIN);
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleService.findByName(ERole.MODERATOR);
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleService.findByName(ERole.USER);
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userService.save(user);

        return ResponseEntity.ok(new MessageResponse("Registrado com sucesso"));
    }
}
