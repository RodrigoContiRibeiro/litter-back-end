package com.litter.litter.controller;

import com.google.firebase.internal.FirebaseService;
import com.litter.litter.model.MessageFirebase;
import com.litter.litter.service.firebase.FirestoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/firebase")
public class FirebaseController {
    @Autowired
    FirestoreServiceImpl firestoreService;

    @PostMapping("/")
    public ResponseEntity save(@RequestBody MessageFirebase messageFirebase) throws ExecutionException, InterruptedException {
        String result = firestoreService.save(messageFirebase);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
