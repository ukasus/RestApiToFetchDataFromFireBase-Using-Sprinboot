package com.ujjawal.firebaseconnect.services;

import com.google.auth.oauth2.GoogleCredentials;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;




import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.io.*;


@Service
public class firebaseConnection {
    @Value("classpath:cdata.json")
    private Resource res;

    @PostConstruct
    public void initiliaseFirebase() throws IOException {

        File file = File.createTempFile("data", ".json");







        FileInputStream serviceAccount =
                null;
        try {

            InputStream initialStream=res.getInputStream();
            byte[] buffer = new byte[initialStream.available()];
            initialStream.read(buffer);

            OutputStream outStream = new FileOutputStream(file);
            outStream.write(buffer);


            serviceAccount = new FileInputStream(file);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(res.getInputStream()))
                    .setDatabaseUrl("https://springbootconnection-19188.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
