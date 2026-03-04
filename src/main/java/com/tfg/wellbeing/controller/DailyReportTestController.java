
package com.tfg.wellbeing.controller;

import com.tfg.wellbeing.model.Achievements;
import com.tfg.wellbeing.model.Alerts;
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
    private final JDBCAlertsManager alertsManager;

    public DailyReportTestController(JDBCDailyReportManager reportManager,
                                           JDBCDailySymptomsManager symptomsManager,
                                           JDBCGamificationManager gamification,JDBCPatientAchievements patientAchievements,
                                     JDBCAchievementsManager achievements,JDBCAlertsManager alertsManager) {
        this.reportManager = reportManager;
        this.symptomsManager = symptomsManager;
        this.gamification = gamification;
        this.patientAchievements = patientAchievements;
        this.achievements = achievements;
        this.alertsManager = alertsManager;
    }

    @GetMapping("/test/patient/add-full-report")
    public String addFullReport(@RequestParam int patientId,
                                @RequestParam int mood,
                                @RequestParam int medication,
                                @RequestParam String note,
                                @RequestParam String date,
                                @RequestParam(required = false) List<Integer> symptoms) {

        // 1 Insert report
        int reportId = reportManager.addDailyReport(patientId, mood, medication, note, date);

        // 2 Insert symptoms
        if (symptoms != null) {
            for (Integer symptomId : symptoms) {
                symptomsManager.addDailySymptoms(reportId, symptomId);
            }
        }

        // 3 Gamification
        int newTotal = gamification.addPoints(patientId, 10);

        // 4 ALERT CHECK
        if (mood <= 2) {

            if (!alertsManager.hasActiveAlerts(patientId, "LOW_MOOD")) {

                alertsManager.createAlerts(
                        patientId,
                        "LOW_MOOD",
                        0,
                        "Patient reported low mood",
                        date
                );
            }
        }

        return "Report created id=" + reportId +
                " | points now=" + newTotal;
    }
    @GetMapping("/test/patient/alerts")
    public List<Alerts> alerts(@RequestParam int patientId) {
        return alertsManager.getAllAlerts(patientId);
    }
}