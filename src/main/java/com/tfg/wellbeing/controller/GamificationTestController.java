package com.tfg.wellbeing.controller;

import com.tfg.wellbeing.repository.JDBCGamificationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GamificationTestController {

    private final JDBCGamificationManager gamification;

    public GamificationTestController(JDBCGamificationManager gamification) {
        this.gamification = gamification;
    }

    @GetMapping("/test/patient/gamification")
    public String status(@RequestParam int patientId) {
        gamification.createIfNotExists(patientId);
        int points = gamification.getPoints(patientId);
        return "Patient " + patientId + " points: " + points;
    }

    @GetMapping("/test/patient/add-points")
    public String addPoints(@RequestParam int patientId, @RequestParam int points) {
        int newTotal = gamification.addPoints(patientId, points);
        return "New points total: " + newTotal;
    }
    @GetMapping("/test/patient/gamification-row")
    public String row(@RequestParam int patientId) {
        return "Points=" + gamification.getPoints(patientId);
    }


}
