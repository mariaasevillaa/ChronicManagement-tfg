
package com.tfg.wellbeing.controller;

import com.tfg.wellbeing.model.Achievements;
import com.tfg.wellbeing.repository.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DailyReportTestController {

    private final JDBCDailyReportManager reportManager;
    private final JDBCDailySymptomsManager symptomsManager;
    private final JDBCGamificationManager gamification;
    private final JDBCPatientAchievements patientAchievements;
    private final JDBCAchievementsManager achievements;

    public DailyReportTestController(JDBCDailyReportManager reportManager,
                                           JDBCDailySymptomsManager symptomsManager,
                                           JDBCGamificationManager gamification,JDBCPatientAchievements patientAchievements,
                                     JDBCAchievementsManager achievements) {
        this.reportManager = reportManager;
        this.symptomsManager = symptomsManager;
        this.gamification = gamification;
        this.patientAchievements = patientAchievements;
        this.achievements = achievements;
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
        List<Achievements> achievementslist = achievements.getAchievements();

        List<String> unlocked = new ArrayList<>();

        for (Achievements a : achievementslist) {
            if (newTotal >= a.getPoints_reward()) {

                if (!patientAchievements.hasAchievement(patientId, a.getId())) {
                    patientAchievements.addPatientAchievements(patientId, a.getId());
                    unlocked.add(a.getName());
                }
            }
        }


      return "Report created id=" + reportId +
                " | points now=" + newTotal +
                " | new achievements=" + unlocked;
    }
}