
package com.tfg.wellbeing.controller;

import com.tfg.wellbeing.repository.JDBCDailyReportManager;
import com.tfg.wellbeing.repository.JDBCDailySymptomsManager;
import com.tfg.wellbeing.repository.JDBCGamificationManager;
import com.tfg.wellbeing.repository.JDBCPatientAchievements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DailyReportTestController {

    private final JDBCDailyReportManager reportManager;
    private final JDBCDailySymptomsManager symptomsManager;
    private final JDBCGamificationManager gamification;
    private final JDBCPatientAchievements patientAchievements;

    public DailyReportTestController(JDBCDailyReportManager reportManager,
                                           JDBCDailySymptomsManager symptomsManager,
                                           JDBCGamificationManager gamification,JDBCPatientAchievements patientAchievements) {
        this.reportManager = reportManager;
        this.symptomsManager = symptomsManager;
        this.gamification = gamification;
        this.patientAchievements = patientAchievements;
    }

    @GetMapping("/test/patient/add-full-report")
    public String addFullReport(@RequestParam int patientId,
                                @RequestParam int mood,
                                @RequestParam int medication,
                                @RequestParam String note,
                                @RequestParam String date,
                                @RequestParam(required = false) List<Integer> symptoms) {

        // 1) Insert daily_report y obtener id
        int reportId = reportManager.addDailyReport(patientId, mood, medication, note, date);

        // 2) Insertar síntomas (si hay)
        if (symptoms != null) {
            for (Integer symptomId : symptoms) {
                symptomsManager.addDailySymptoms(symptomId,reportId);
            }
        }

        // 3) Gamificación: sumar puntos, mirar lo del hash
        int newTotal = gamification.addPoints(patientId, 10);
        // Si llega a 50 puntos
        if (newTotal >= 50) {

            // id 1 = achievement de 50 puntos
            int achievementId = 1;

                patientAchievements.addPatientAchievements(patientId, achievementId);

        }

        return "Report created id=" + reportId + " | points now=" + newTotal;
    }
}