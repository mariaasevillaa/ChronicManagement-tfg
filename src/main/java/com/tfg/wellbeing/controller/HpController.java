package com.tfg.wellbeing.controller;

import com.tfg.wellbeing.model.Alerts;
import com.tfg.wellbeing.model.Daily_report;
import com.tfg.wellbeing.model.MonitoringParameters;
import com.tfg.wellbeing.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.tfg.wellbeing.model.Patient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HpController {

    private final JDBCHpPatientManager patientManager;
    private final JDBCMonitoringParameters monitoringParameters;
    private final JDBCAlertsManager alertsManager;
    private final JDBCDailyReportManager dailyReportManager;
    private final JDBCPatientManager patientManager1;


    public HpController(JDBCHpPatientManager patientManager, JDBCMonitoringParameters monitoringParameters, JDBCAlertsManager alertsManager, JDBCDailyReportManager dailyReportManager, JDBCPatientManager patientManager1) {
        this.patientManager = patientManager;
        this.monitoringParameters = monitoringParameters;
        this.alertsManager = alertsManager;
        this.dailyReportManager = dailyReportManager;
        this.patientManager1 = patientManager1;
    }

    @GetMapping("/hp_dashboard")
    public String hpDashboard(Model model, HttpSession session) {
        Integer user_id = (Integer) session.getAttribute("user_id");
        if (user_id == null) {
            return "redirect:/login";
        }
        model.addAttribute("user_id", user_id);
        String name= (String) session.getAttribute("name");
        model.addAttribute("name", name);
        List<Patient>patientList=patientManager1.getAllPatients();
        model.addAttribute("Patientlist", patientList);
        return "hp_dashboard";

    }
    @PostMapping("/select_patient")
    public String selectPatient(@RequestParam("patient_id") int patientId,
                                HttpSession session){

        Integer doctorId = (Integer) session.getAttribute("doctor_id");
        String role = (String) session.getAttribute("role");

        if (doctorId == null || role == null || !role.equalsIgnoreCase("DOCTOR")) {
            return "redirect:/login";
        }

        patientManager.assignPatientToHp(doctorId, patientId);

        return "redirect:/view_patient";
    }



    @GetMapping("/review_reports")
    public String reviewReports(Model model, @RequestParam int patient_id) {
        List<Daily_report> dailyReportList= dailyReportManager.getReportByPatientId(patient_id);
        model.addAttribute("dailyReports", dailyReportList);
        return "review_reports";

    }
    @GetMapping("/configure_parameters")
    public String configureParameters(Model model, @RequestParam int patient_id) {
        MonitoringParameters mp= monitoringParameters.getParametersbyPatientId(patient_id);
        model.addAttribute("monitoringParameters", mp);
        return "configure_parameters";
    }
    @GetMapping("/patient_alerts")
    public String patientAlerts(Model model, @RequestParam int patient_id) {
        List<Alerts>alertsList=alertsManager.getAllAlerts(patient_id);
        model.addAttribute("alertsList", alertsList);
        return "patient_alerts";
    }

}
