package com.ujjawal.firebaseconnect.Controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.ujjawal.firebaseconnect.dto.User;
import com.ujjawal.firebaseconnect.services.firebaseservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin
public class RestController {

@Autowired
User user;
@Autowired
    firebaseservices firebaseServices;

    @RequestMapping("/users")
    public User users()
    {
       user=new User();
       user.setFirstName("Ujjawal");
       user.setLastName("Sharma");
       user.setEmail("ujjawal98kaushik@gmail.com");
       user.setPhone("9012802194");

        return user;

    }
    @RequestMapping("/addUser")
    public String saveUserDetails(@RequestBody User person) throws ExecutionException, InterruptedException {
        return firebaseServices.saveUserDetails(person);
    }

    @RequestMapping(value = "/getUser/{email}" ,method = RequestMethod.POST)
    public User getUserDetails(@PathVariable("email") String email) throws ExecutionException, InterruptedException {
        return firebaseServices.getUserDetails(email);
    }

    @RequestMapping(value = "/getUser" ,method = RequestMethod.GET)
    public List<User> getUsers() throws ExecutionException, InterruptedException {
        return firebaseServices.getUsers();
    }

}
