package com.tfg.wellbeing.controller;

import com.tfg.wellbeing.model.Health_professional;
import com.tfg.wellbeing.model.User;
import com.tfg.wellbeing.repository.JDBCHealthCareManager;
import com.tfg.wellbeing.repository.JDBCPatientManager;
import com.tfg.wellbeing.repository.JDBCUserManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.tfg.wellbeing.model.Patient;
import org.springframework.ui.Model;

@Controller
public class AuthController {

    private final JDBCUserManager userManager;
    private final JDBCPatientManager patientManager;
    private final JDBCHealthCareManager healthCareManager;



    public AuthController(JDBCUserManager userManager, JDBCPatientManager patientManager, JDBCHealthCareManager healthCareManager) {
        this.userManager = userManager;
        this.patientManager = patientManager;
        this.healthCareManager = healthCareManager;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String surname, @RequestParam String date_of_birth, @RequestParam String chronic_condition,
                           @RequestParam String diagnosis_date,@RequestParam String email,  @RequestParam String password, Model model, @RequestParam (required = false) String consent) {
        if(consent==null){
            model.addAttribute("error", "You must acccept the processing of your personal health and wellbeing data.");
            return "register";
        }
        if(userManager.existsEmail(email)) {
            model.addAttribute("error", "Email already exists");
            return "register";

        }
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
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session,Model model) {
        User user = userManager.getUserByEmail(email);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if(user== null||!passwordEncoder.matches(password, user.getPassword())) {
            model.addAttribute("error", "Incorrect email or password.");
            return "login";

        }
        session.setAttribute("user_id", user.getId());
        session.setAttribute("role", user.getRole());

        if(user.getRole().equals("patient")) {
            Patient patient = patientManager.getPatientByEmail(email);
            if(patient != null) {
                session.setAttribute("name",patient.getName());
                session.setAttribute("patient_id", patient.getId());
            }
            System.out.println("Patient logged in, going to patient dashboard");
            return "redirect:/patient_dashboard";
        }
        if(user.getRole().equals("DOCTOR")) {
            Health_professional healthProfessional= healthCareManager.getHealthProfessionalByEmail(email);
            System.out.println("LOGIN doctor user = " + user.getEmail());
            System.out.println("LOGIN role = " + user.getRole());
            System.out.println("LOGIN healthProfessional = " + healthProfessional);
            if(healthProfessional != null) {
                session.setAttribute("name",healthProfessional.getName());
                session.setAttribute("healthprofessional_id", healthProfessional.getId());
                System.out.println("LOGIN doctor_id saved = " + healthProfessional.getId());

            }

            System.out.println("Healthcare professional logged in, going to healthcare professional dashboard");
            return "redirect:/hp_dashboard";
        }
        return "login";

    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    @GetMapping("/forgot_password")
    public String forgotPassword() {
        return "forgot_password";
    }

    @PostMapping("/forgot_password")
    public String forgotPassword(@RequestParam String email, HttpSession session, Model model) {
        User user = userManager.getUserByEmail(email);

        if (user == null) {
            model.addAttribute("error", "Email not found");
            return "forgot_password";
        }
        session.setAttribute("reset_email", email);
        return "redirect:/reset_password";
    }
    @GetMapping("/reset_password")
    public String resetPassword(HttpSession session) {
        String email = (String) session.getAttribute("reset_email");

        if (email == null) {
            return "redirect:/forgot_password";
        }

        return "reset_password";
    }

    @PostMapping("/reset_password")
    public String resetPassword(@RequestParam String password,
                                @RequestParam String confirmPassword,
                                HttpSession session,
                                Model model) {
        String email = (String) session.getAttribute("reset_email");

        if (email == null) {
            return "redirect:/forgot_password";
        }

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "reset_password";
        }

        userManager.updatePassword(email, password);

        session.removeAttribute("reset_email");

        return "redirect:/login";
    }
    @GetMapping("/about_us")
    public String aboutUs(HttpSession session) {
        return "about_us";
    }
    @GetMapping("/how_works")
    public String howWorks(HttpSession session) {
        return "how_works";
    }
    @GetMapping("/frontPage")
    public String frontPage(HttpSession session) {
        return "frontPage";
    }

}
