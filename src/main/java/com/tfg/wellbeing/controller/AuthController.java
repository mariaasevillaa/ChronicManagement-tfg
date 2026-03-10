package com.tfg.wellbeing.controller;

import com.tfg.wellbeing.model.User;
import com.tfg.wellbeing.repository.JDBCUserManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final JDBCUserManager userManager;


    public AuthController(JDBCUserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping("/register")
    public String register() {
        return "Register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String email,  @RequestParam String password) {
        userManager.addUser(email, password, "patient");
        return "login";

    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String email,@RequestParam String password) {
        User user = userManager.getUserByEmail(email);
        if(user == null) {
            return "login";
        }
        if(!user.getPassword().equals(password)) {
            return "login";

        }
        if(user.getRole().equals("patient")) {
            return "patient_dashboard";
        }
        if(user.getRole().equals("healthcare professional")) {
            return "professional_dashboard";
        }
        return "login";

    }
}
