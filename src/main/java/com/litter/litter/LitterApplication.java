package com.litter.litter;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class LitterApplication {

    public static void main(String[] args) throws IOException {
        InputStream account = new FileInputStream("src/main/resources/litter-8060d-50b7309b533b.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(account);
        FirebaseOptions firebaseOptions = FirebaseOptions.builder().setCredentials(credentials).build();

        boolean hasBeenInitialized = false;
        List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
        for (FirebaseApp firebaseApp : firebaseApps) {
            if (firebaseApp.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
                hasBeenInitialized = true;
            }
        }
        if (!hasBeenInitialized) {
            FirebaseApp.initializeApp(firebaseOptions);
        }

        SpringApplication.run(LitterApplication.class, args);
    }

}
