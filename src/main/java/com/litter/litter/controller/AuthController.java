package com.litter.litter.controller;

import com.litter.litter.model.User;
import com.litter.litter.payload.request.auth.LoginRequest;
import com.litter.litter.payload.request.auth.RegisterRequest;
import com.litter.litter.payload.response.auth.JwtResponse;
import com.litter.litter.payload.response.MessageResponse;
import com.litter.litter.security.jwt.JwtUtils;
import com.litter.litter.security.userDetails.UserDetailsImpl;
import com.litter.litter.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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
        ResponseEntity isValid = userService.validateFields(registerRequest);

        if(isValid!=null) {
            //If is not valid will return a ResponseEntity with an error
            return isValid;
        }

        //Forming roles
        User user = userService.buildUserForSave(registerRequest);

        userService.save(user);

        return ResponseEntity.ok(new MessageResponse("Registrado com sucesso"));
    }
}
