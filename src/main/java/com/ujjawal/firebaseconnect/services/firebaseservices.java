package com.ujjawal.firebaseconnect.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.ujjawal.firebaseconnect.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class firebaseservices {




    public String saveUserDetails(User person) throws InterruptedException, ExecutionException {
        Firestore dbFirestore=FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("persons").document(person.getEmail()).set(person);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public User getUserDetails(String email) throws InterruptedException, ExecutionException {
        Firestore dbFirestore=FirestoreClient.getFirestore();
        DocumentReference doc=dbFirestore.collection("persons").document(email);
        ApiFuture<DocumentSnapshot> docdata=doc.get();
        DocumentSnapshot document=docdata.get();
        User user=document.toObject(User.class);
        return user;
    }
    public List<User> getUsers() throws InterruptedException, ExecutionException {
        List<User> users=new ArrayList<>();
        Firestore dbFirestore=FirestoreClient.getFirestore();
        CollectionReference coll=dbFirestore.collection("persons");
        ApiFuture<QuerySnapshot> query=coll.get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
           User user=document.toObject(User.class);
           users.add(user);
        }

        return users;
    }
}
