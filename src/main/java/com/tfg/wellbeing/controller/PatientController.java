package com.tfg.wellbeing.controller;

import com.tfg.wellbeing.model.Achievements;
import com.tfg.wellbeing.model.Daily_report;
import com.tfg.wellbeing.model.Patient;
import com.tfg.wellbeing.model.Symptoms;
import com.tfg.wellbeing.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class PatientController {
    private final JDBCPatientManager patientManager;
    private final JDBCDailyReportManager dailyReportManager;
    private final JDBCSymptomsManager symptomsManager;
    private final JDBCDailySymptomsManager dailySymptomsManager;
    private final JDBCAchievementsManager achievementsManager;
    private final JDBCPatientAchievements patientAchievementsManager;
    private final JDBCGamificationManager gamificationManager;
    private final JDBCAlertsManager alertsManager;

    public PatientController(JDBCPatientManager patientManager, JDBCDailyReportManager dailyReportManager, JDBCSymptomsManager symptomsManager, JDBCDailySymptomsManager dailySymptomsManager, JDBCAchievementsManager achievementsManager, JDBCPatientAchievements patientAchievementsManager, JDBCGamificationManager gamificationManager, JDBCAlertsManager alertsManager) {
        this.patientManager = patientManager;
        this.dailyReportManager = dailyReportManager;
        this.symptomsManager = symptomsManager;
        this.dailySymptomsManager = dailySymptomsManager;
        this.achievementsManager = achievementsManager;
        this.patientAchievementsManager = patientAchievementsManager;
        this.gamificationManager = gamificationManager;
        this.alertsManager = alertsManager;
    }
    @GetMapping("/patient_dashboard")
    public String patientDashboard() {
        return "patient_dashboard";
    }
    @GetMapping("/daily_reports")
    public String dailyReports() {
        return "daily_reports";
    }
    @PostMapping("/daily_reports")
    public String dailyReports(@RequestParam int patient_id, @RequestParam int mood, @RequestParam int medication_taken,@RequestParam String note,@RequestParam String date, @RequestParam List<Integer> symptoms ) {
        int report_id= dailyReportManager.addDailyReport(patient_id,mood,medication_taken,note,date);
        if(symptoms != null) {
            for(Integer symptom_id : symptoms) {
                dailySymptomsManager.addDailySymptoms(symptom_id,report_id);
            }
        }
         int points=  gamificationManager.addPoints(patient_id,10);
        List<Achievements> achievements= achievementsManager.getAchievements();
        for(Achievements a : achievements) {
            if(points>=a.getPoints_reward()){
                if(!patientAchievementsManager.hasAchievement(patient_id,a.getId())){
                    patientAchievementsManager.addPatientAchievements(patient_id,a.getId());
                }
            }
        }
        if(mood<=2){
            if(!alertsManager.hasActiveAlerts(patient_id,"LOW_MOOD")){
                alertsManager.createAlerts(patient_id,"LOW_MOOD",0,"Patient reported low_mood",date);

            }
        }
        return "redirect:/patient_dashboard";

    }

    @GetMapping("/patient_progress")
    public String patientProgress(@RequestParam int patient_id, Model model) {
        int total_reports= dailyReportManager.getTotalReportsByPatientId(patient_id);
        model.addAttribute("Totalreports : ",total_reports);
        double average_mood =dailyReportManager.getAverageMood(patient_id);
        model.addAttribute("Averagemood : ",average_mood);
        double average_medication = dailyReportManager.getAverageMedicationTaken(patient_id);
        model.addAttribute("Averagemedicationtaken : ",average_medication);
        return "patient_progress";

    }

    @GetMapping("/patient_gamification")
    public String patientGamification(@RequestParam int patient_id, Model model) {
        int points= gamificationManager.getPoints(patient_id);
        model.addAttribute("points : ",points);
        return "patient_gamification";
    }




}
