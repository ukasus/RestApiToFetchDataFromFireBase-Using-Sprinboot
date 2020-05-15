package com.ujjawal.firebaseconnect.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class firebaseConnection {

    @PostConstruct
    public void initiliaseFirebase()

    {
          FileInputStream serviceAccount =
                null;
        try {
            File file = ResourceUtils.getFile("classpath:cdata.json");


            serviceAccount = new FileInputStream(file.getPath());
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://springbootconnection-19188.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
