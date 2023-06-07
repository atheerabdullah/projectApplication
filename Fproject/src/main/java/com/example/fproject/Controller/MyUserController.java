package com.example.fproject.Controller;


import com.example.fproject.Model.MyUser;
import com.example.fproject.Service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class MyUserController {

    private final MyUserService myUserService;

    @GetMapping("/get")
    private ResponseEntity AllUsers(){
        List<MyUser> AllUsers=  myUserService.getAllUsers();
        return ResponseEntity.status(200).body(AllUsers);
    }
    @PostMapping("/registerForUser")
    public ResponseEntity register(@RequestBody MyUser myUser){
        myUserService.register(myUser);
        return ResponseEntity.status(200).body("User Register");
    }
    @PostMapping("/admin")
    public ResponseEntity admin(){
        return ResponseEntity.status(200).body("Welcome Admin");
    }
    @PostMapping("/user")
    public ResponseEntity user(){
        return ResponseEntity.status(200).body("Welcome user");
    }
    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body("login");
    }
    @PostMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body("logout");
    }
}


