package com.litter.litter.controller;

import com.litter.litter.model.User;
import com.litter.litter.payload.request.user.GetUserRequest;
import com.litter.litter.payload.response.MessageResponse;
import com.litter.litter.payload.response.user.GetUserResponse;
import com.litter.litter.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    PasswordEncoder encoder;

    // GET to obtain user data
    @GetMapping("/")
    public ResponseEntity getByID(@Valid @RequestBody GetUserRequest getUserRequest){
        User user = userService.findById(getUserRequest.getId());

        if(user.getId() == null && user.getUsername() == null && user.getEmail() == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Usuário não encontrado"));
        }

        return ResponseEntity.ok().body(new GetUserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getRoles()));
    };
    // POST to register user data(in the auth controller)

    // DELETE to remove user data
}
