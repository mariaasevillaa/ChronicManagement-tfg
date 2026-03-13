package com.tfg.wellbeing.controller;

import com.tfg.wellbeing.model.Alerts;
import com.tfg.wellbeing.model.Daily_report;
import com.tfg.wellbeing.model.MonitoringParameters;
import org.springframework.ui.Model;
import com.tfg.wellbeing.model.Patient;
import com.tfg.wellbeing.repository.JDBCAlertsManager;
import com.tfg.wellbeing.repository.JDBCDailyReportManager;
import com.tfg.wellbeing.repository.JDBCHpPatientManager;
import com.tfg.wellbeing.repository.JDBCMonitoringParameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HpController {

    private final JDBCHpPatientManager patientManager;
    private final JDBCMonitoringParameters monitoringParameters;
    private final JDBCAlertsManager alertsManager;
    private final JDBCDailyReportManager dailyReportManager;


    public HpController(JDBCHpPatientManager patientManager, JDBCMonitoringParameters monitoringParameters, JDBCAlertsManager alertsManager, JDBCDailyReportManager dailyReportManager) {
        this.patientManager = patientManager;
        this.monitoringParameters = monitoringParameters;
        this.alertsManager = alertsManager;
        this.dailyReportManager = dailyReportManager;
    }

    @GetMapping("/hp_dashboard")
    public String hpDashboard() {
        return "hp_dashboard";

    }

    @GetMapping("/patient_list")
    public String patientList(Model model, @RequestParam int hp_id) {
        List<Patient> patientList= patientManager.getPatientByHpId(hp_id);
        model.addAttribute("Patientlist", patientList);
        return "patient_list";
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
