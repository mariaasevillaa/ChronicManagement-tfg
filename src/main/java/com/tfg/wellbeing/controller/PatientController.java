package com.tfg.wellbeing.controller;

import com.tfg.wellbeing.model.Achievements;
import com.tfg.wellbeing.model.Daily_report;
import com.tfg.wellbeing.model.Patient;
import com.tfg.wellbeing.model.Symptoms;
import com.tfg.wellbeing.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.ArrayList;
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
    public String patientDashboard(HttpSession session, Model model) {
        Integer user_id = (Integer) session.getAttribute("user_id");
        if (user_id == null) {
            return "redirect:/login";
        }
        model.addAttribute("user_id", user_id);
        String name= (String) session.getAttribute("name");
        model.addAttribute("name", name);

        return "patient_dashboard";
    }
    @GetMapping("/patient_profile")
    public String patientProfile(HttpSession session, Model model) {
        Integer user_id = (Integer) session.getAttribute("user_id");
        int patient_id= patientManager.getPatientIDbyUserID(user_id);
        Patient patient= patientManager.getPatientbyID(patient_id);
        model.addAttribute("patient_id", patient_id);

        model.addAttribute("patient_id", patient.getId());
        model.addAttribute("name", patient.getName());
        model.addAttribute("surname", patient.getSurname());
        model.addAttribute("date_of_birth", patient.getDate_of_birth());
        model.addAttribute("chronic_condition", patient.getChronic_condition());
        model.addAttribute("diagnosis_date", patient.getDiagnosis_date());
        return "patient_profile";

    }
    @GetMapping("/edit_profile")
    public String editProfile(HttpSession session, Model model) {
        Integer user_id = (Integer) session.getAttribute("user_id");
        int patient_id= patientManager.getPatientIDbyUserID(user_id);
        model.addAttribute("patient_id", patient_id);
        Patient patient= patientManager.getPatientbyID(patient_id);
        model.addAttribute("patient", patient);
        model.addAttribute("name",patient.getName());

        return "edit_profile";
    }
    @PostMapping("/edit_profile")
    public String editProfile( HttpSession session, Model model,@RequestParam String name, @RequestParam String surname, @RequestParam String chronic_condition, @RequestParam String diagnosis_date) {
        Integer user_id = (Integer) session.getAttribute("user_id");
        int patient_id= patientManager.getPatientIDbyUserID(user_id);
        model.addAttribute("patient_id", patient_id);
        patientManager.updatePatientProfile(patient_id, name, surname, chronic_condition, diagnosis_date);
       session.setAttribute("name", name);
       session.setAttribute("surname", surname);
       session.setAttribute("chronic_condition", chronic_condition);
       session.setAttribute("diagnosis_date", diagnosis_date);
       return "redirect:/patient_profile";
    }

    @GetMapping("/daily_reports")
    public String dailyReports(HttpSession session,Model model) {
        Integer user_id = (Integer) session.getAttribute("user_id");
        int patient_id= patientManager.getPatientIDbyUserID(user_id);
        model.addAttribute("patient_id", patient_id);
        String name= (String) session.getAttribute("name");
        model.addAttribute("name", name);
        return "daily_reports";
    }
    @PostMapping("/daily_reports")
    public String dailyReports(HttpSession session, @RequestParam int mood, @RequestParam int medication_taken,@RequestParam String note,@RequestParam String date, @RequestParam List<Integer> symptoms, Model model ) {
        Integer user_id = (Integer) session.getAttribute("user_id");
        int patient_id= patientManager.getPatientIDbyUserID(user_id);
        int report_id= dailyReportManager.addDailyReport(patient_id,mood,medication_taken,note,date);
        if(symptoms != null) {
            for(Integer symptom_id : symptoms) {
                dailySymptomsManager.addDailySymptoms(symptom_id,report_id);
            }
        }
         int points=  gamificationManager.addPoints(patient_id,10);
        int reportsCount = dailyReportManager.countReportsByPatientId(patient_id);
        List<Achievements> achievements = achievementsManager.getAchievements();

        for (Achievements a : achievements) {
            if (reportsCount >= a.getReports_needed()) {
                if (!patientAchievementsManager.hasAchievement(patient_id, a.getId())) {
                    patientAchievementsManager.addPatientAchievements(patient_id, a.getId());
                }
            }
        }
        if(mood<=2){
            if(!alertsManager.hasActiveAlerts(patient_id,"LOW_MOOD")){
                alertsManager.createAlerts(patient_id,"LOW_MOOD",0,"Patient reported low mood",date);

            }
        }
        if(medication_taken==0){
            System.out.println("entra NO_MEDICATION");
            if(!alertsManager.hasActiveAlerts(patient_id,"NO_MEDICATION")){
                alertsManager.createAlerts(patient_id,"NO_MEDICATION",0,"Patient hasn't taken the medication",date);

            }
        }
        String name= (String) session.getAttribute("name");
        model.addAttribute("name",name);
        return "redirect:/patient_dashboard";

    }

    @GetMapping("/patient_progress")
    public String patientProgress(HttpSession session, Model model) {
        Integer user_id = (Integer) session.getAttribute("user_id");
        int patient_id= patientManager.getPatientIDbyUserID(user_id);
        int total_reports= dailyReportManager.getTotalReportsByPatientId(patient_id);
        model.addAttribute("Totalreports",total_reports);
        double average_mood =dailyReportManager.getAverageMood(patient_id);
        model.addAttribute("Averagemood",average_mood);
        double average_medication = dailyReportManager.getAverageMedicationTaken(patient_id);
        model.addAttribute("Averagemedicationtaken",average_medication);
        String name= (String) session.getAttribute("name");
        model.addAttribute("name", name);
        model.addAttribute("daysvalues", dailyReportManager.getDaysValues(patient_id));
        model.addAttribute("dayslabels", dailyReportManager.getDaysLabel(patient_id));
        model.addAttribute("weeksvalues", dailyReportManager.getWeekValues(patient_id));
        model.addAttribute("weekslabels", dailyReportManager.getWeekLabels(patient_id));
        model.addAttribute("monthsvalues", dailyReportManager.getMonthValues(patient_id));
        model.addAttribute("monthslabels", dailyReportManager.getMonthLabels(patient_id));
        model.addAttribute("medicationdays",dailyReportManager.getDaysValuesMed(patient_id));
        model.addAttribute("medicationweeks",dailyReportManager.getWeekValuesMed(patient_id));
        model.addAttribute("medicationmonths",dailyReportManager.getMonthValuesMed(patient_id));
        return "patient_progress";

    }

    @GetMapping("/patient_gamification")
    public String patientGamification(HttpSession session, Model model) {
        Integer user_id = (Integer) session.getAttribute("user_id");
        int patient_id= patientManager.getPatientIDbyUserID(user_id);
        int points= gamificationManager.getPoints(patient_id);
        int streakdays= gamificationManager.getStreakDayss(patient_id);
        int reportsCount=dailyReportManager.countReportsByPatientId(patient_id);
        List<Achievements> unlockedAchievements=patientAchievementsManager.getAchievemntsbyPatientId(patient_id);
        List<Achievements> allAchievements=achievementsManager.getAchievements();
        Achievements nextAchievement = null;
        for (Achievements a : allAchievements) {
            if (reportsCount < a.getReports_needed()) {
                nextAchievement = a;
                break;
            }
        }
        model.addAttribute("nextachievement",nextAchievement);
        model.addAttribute("points",points);
        model.addAttribute("streakdays",streakdays);
        model.addAttribute("unlockedachievements",unlockedAchievements);
        model.addAttribute("allachievements",allAchievements);
        model.addAttribute("reportsCount", reportsCount);
        String name= (String) session.getAttribute("name");
        model.addAttribute("name", name);
        return "patient_gamification";
    }

    @GetMapping("/patient_achievements")
    public String patientAchievements(HttpSession session, Model model) {
        Integer user_id = (Integer) session.getAttribute("user_id");
        int patient_id= patientManager.getPatientIDbyUserID(user_id);
        int points= gamificationManager.getPoints(patient_id);
        int reportsCount= dailyReportManager.countReportsByPatientId(patient_id);
        List<Achievements> unlockedAchievements=patientAchievementsManager.getAchievemntsbyPatientId(patient_id);
        List<Achievements> allAchievements=achievementsManager.getAchievements();
        List<Achievements> lockedAchievements = new ArrayList<>();

        for (Achievements a : allAchievements) {
            if (reportsCount >= a.getReports_needed()) {
                unlockedAchievements.add(a);
            } else {
                lockedAchievements.add(a);
            }
        }
        model.addAttribute("points",points);
        model.addAttribute("unlockedAchievements", unlockedAchievements);
        model.addAttribute("lockedAchievements", lockedAchievements);
        model.addAttribute("unlockedAchievementssize", unlockedAchievements.size());

        String name = (String) session.getAttribute("name");
        model.addAttribute("name", name);

        return "patient_achievements";

    }



}
