package com.tfg.wellbeing.controller;

import com.tfg.wellbeing.model.Symptoms;
import com.tfg.wellbeing.repository.JDBCSymptomsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SymptomsTestController {

    private final JDBCSymptomsManager symptomsManager;

    public SymptomsTestController(JDBCSymptomsManager symptomsManager) {
        this.symptomsManager = symptomsManager;
    }

    @GetMapping("/test/symptoms")
    public List<Symptoms> getSymptoms() {
        return symptomsManager.listSymptoms();
    }
}
