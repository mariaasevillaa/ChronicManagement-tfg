package com.tfg.wellbeing.controller;

import com.tfg.wellbeing.repository.JDBCUserManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserTestController {


    private final JDBCUserManager userManager;

    public UserTestController(JDBCUserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping("/test/add-user")
    public String addUser(@RequestParam String email, @RequestParam String password) {

        //if (userManager.existsUser(email)) {
           // return "User already exists";
        //}

        userManager.addUser(email, password, "patient");
        return "Inserted";
    }
}
