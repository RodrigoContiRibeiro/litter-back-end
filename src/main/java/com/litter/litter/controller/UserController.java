package com.litter.litter.controller;

import com.litter.litter.model.User;
import com.litter.litter.payload.request.user.GetUserByNameRequest;
import com.litter.litter.payload.request.user.GetUserRequest;
import com.litter.litter.payload.response.MessageResponse;
import com.litter.litter.payload.response.user.GetUserByNameResponse;
import com.litter.litter.payload.response.user.GetUserResponse;
import com.litter.litter.payload.response.user.ListUserResponse;
import com.litter.litter.service.user.UserServiceImpl;
import com.litter.litter.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/find")
    public ResponseEntity getByName(@RequestParam String username) {
        User user = userService.findByUsername(username);

        if(user.getId() == null && user.getUsername() == null && user.getEmail() == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Usuário não encontrado"));
        }

        return ResponseEntity.ok().body(new GetUserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getRoles()));
    };

    @GetMapping("/list")
    public ResponseEntity<ListUserResponse> list() {
        return new ResponseEntity<>(new ListUserResponse(new MyUtils().removePasswordFromUserList(userService.findAll())), HttpStatus.OK);
    };
}
