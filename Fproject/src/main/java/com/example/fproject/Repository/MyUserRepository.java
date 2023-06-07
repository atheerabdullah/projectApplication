package com.example.fproject.Repository;


import com.example.fproject.Model.MyUser;

import com.example.fproject.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser , Integer> {

    MyUser findMyUserById(int id);
    MyUser findMyUserByUsername(String username);




}
