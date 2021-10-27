package com.litter.litter.service.firebase;

import com.litter.litter.model.MessageFirebase;

import java.util.concurrent.ExecutionException;

public interface FirestoreService {
    public String save(MessageFirebase messageFirebase) throws ExecutionException, InterruptedException;
}
