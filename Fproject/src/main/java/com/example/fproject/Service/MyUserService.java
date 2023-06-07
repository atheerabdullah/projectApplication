package com.example.fproject.Service;

import com.example.fproject.Model.MyUser;
import com.example.fproject.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserService {
    private  final MyUserRepository myUserRepository;

    public List<MyUser> getAllUsers(){
        List<MyUser> AllUsers=myUserRepository.findAll();
        return AllUsers;
    }
    public void register(MyUser myUser){
        String hash=new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hash);
        myUser.setRole("customer");
        myUserRepository.save(myUser);
    }



}
