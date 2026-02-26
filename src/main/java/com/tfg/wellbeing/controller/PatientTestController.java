package com.tfg.wellbeing.controller;

import com.tfg.wellbeing.repository.JDBCPatientManager;
import com.tfg.wellbeing.repository.JDBCUserManager;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientTestController {

    private final JDBCPatientManager patientManager;
    private final JDBCUserManager userManager;

    public PatientTestController(JDBCPatientManager patientManager, JDBCUserManager userManager) {
        this.patientManager = patientManager;
        this.userManager = userManager;
    }

    @GetMapping("/test/add-patient")
    public String addPatient(@RequestParam String email,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String dob,
                             @RequestParam String condition,
                             @RequestParam String diagnosisDate) {

        Integer userId = userManager.existsUser(email);
        if (userId == null) return "User not found";

        patientManager.addPatient(userId, name, surname, dob, condition, diagnosisDate);
        return "Patient inserted";
    }
}