package com.tfg.wellbeing.controller;

import com.tfg.wellbeing.model.User;
import com.tfg.wellbeing.repository.JDBCPatientManager;
import com.tfg.wellbeing.repository.JDBCUserManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final JDBCUserManager userManager;
    private final JDBCPatientManager patientManager;


    public AuthController(JDBCUserManager userManager, JDBCPatientManager patientManager) {
        this.userManager = userManager;
        this.patientManager = patientManager;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String surname, @RequestParam String date_of_birth, @RequestParam String chronic_condition,
                           @RequestParam String diagnosis_date,@RequestParam String email,  @RequestParam String password) {
        int user_id=userManager.addUser(email, password, "patient");
        patientManager.addPatient(user_id,name,surname,date_of_birth,chronic_condition,diagnosis_date);
        return "redirect:/login";

    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    //HttpSession is used to save the data if the log in is successfull
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        User user = userManager.getUserByEmail(email);
        if(user == null) {
            return "login";
        }
        if(!user.getPassword().equals(password)) {
            System.out.println("Wrong password");
            return "login";

        }
        session.setAttribute("user_id", user.getId());
        session.setAttribute("role", user.getRole());

        if(user.getRole().equals("patient")) {
            System.out.println("Patient logged in, going to patient dashboard");
            return "redirect:/patient_dashboard";
        }
        if(user.getRole().equals("healthcare professional")) {
            System.out.println("Healthcare professional logged in, going to healthcare professional dashboard");
            return "redirect:/hp_dashboard";
        }
        return "login";

    }
}
