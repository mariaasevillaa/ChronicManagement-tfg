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

import java.util.ArrayList;
import java.util.List;

@Controller
public class HpController {

    private final JDBCHpPatientManager patientManager;
    private final JDBCMonitoringParameters monitoringParameters;
    private final JDBCAlertsManager alertsManager;
    private final JDBCDailyReportManager dailyReportManager;
    private final JDBCPatientManager patientManager1;
    private final JDBCHealthCareManager healthCareManager;
    private final JDBCDailySymptomsManager dailySymptomsManager;


    public HpController(JDBCHpPatientManager patientManager, JDBCMonitoringParameters monitoringParameters, JDBCAlertsManager alertsManager, JDBCDailyReportManager dailyReportManager, JDBCPatientManager patientManager1, JDBCHealthCareManager healthCareManager, JDBCDailySymptomsManager dailySymptomsManager) {
        this.patientManager = patientManager;
        this.monitoringParameters = monitoringParameters;
        this.alertsManager = alertsManager;
        this.dailyReportManager = dailyReportManager;
        this.patientManager1 = patientManager1;
        this.healthCareManager = healthCareManager;
        this.dailySymptomsManager = dailySymptomsManager;
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
    public String selectPatient(@RequestParam("patient_id") int patientId,@RequestParam("assign") boolean assign,HttpSession session, Model model){
        Integer user_id = (Integer) session.getAttribute("user_id");
        int hp_id= healthCareManager.getHpIDbyUserID(user_id);
        String role = (String) session.getAttribute("role");
        System.out.println("doctorId = " + hp_id);
        System.out.println("role = " + role);
        System.out.println("patientId = " + patientId);
        System.out.println("assign = " + assign);
        if (hp_id == 0 || role == null || !role.equalsIgnoreCase("DOCTOR")) {
            return "redirect:/login";
        }
        if(assign){
            patientManager.assignPatientToHp(hp_id, patientId);
        }

        model.addAttribute("selectedPatient_id", patientId);
        Patient patient = patientManager1.getPatientbyID(patientId);

        if (patient != null) {
            session.setAttribute("selected_patient_name", patient.getName());
        }

        return "redirect:/view_patient?patient_id=" + patientId;
    }
    @GetMapping("/view_patient")
    public String view_patient(Model model, HttpSession session, @RequestParam("patient_id") int patientId) {

        String name = (String) session.getAttribute("name");

        Patient patient= patientManager1.getPatientbyID(patientId);

        if (patientId == 0) {
            return "redirect:/hp_dashboard";
        }
        model.addAttribute("name", name);
        model.addAttribute("patient_id",patientId);
        model.addAttribute("pname", patient.getName());
        model.addAttribute("psurname", patient.getSurname());
        model.addAttribute("dob", patient.getDate_of_birth());
        model.addAttribute("chronic", patient.getChronic_condition());
        model.addAttribute("diagnosis", patient.getDiagnosis_date());
        return "view_patient";
    }


    @GetMapping("/review_reports")
    public String reviewReports(Model model, @RequestParam ("patient_id") int patient_id,HttpSession session) {
        List<Daily_report> dailyReportList= dailyReportManager.getReportByPatientId(patient_id);
        model.addAttribute("dailyReports", dailyReportList);
        List<String>reportswithsymptoms= new ArrayList<>();
        String name = (String) session.getAttribute("name");

        Patient patient= patientManager1.getPatientbyID(patient_id);

        if (patient_id == 0) {
            return "redirect:/hp_dashboard";
        }
        for(Daily_report report: dailyReportList){
            List<String>symptoms= dailySymptomsManager.getSymptomsByReportId(report.getId());
            reportswithsymptoms.addAll(symptoms);
        }
        model.addAttribute("reportswithsymptoms", reportswithsymptoms);
        model.addAttribute("name", name);
        model.addAttribute("pname", patient.getName());
        model.addAttribute("psurname", patient.getSurname());
        model.addAttribute("patient_id", patient.getId());

        return "review_reports";

    }
    @GetMapping("/progress")
    public String patientProgress(HttpSession session, Model model,@RequestParam("patient_id") int patient_id) {
        int total_reports= dailyReportManager.getTotalReportsByPatientId(patient_id);
        model.addAttribute("Totalreports",total_reports);
        double average_mood =dailyReportManager.getAverageMood(patient_id);
        model.addAttribute("Averagemood",average_mood);
        double average_medication = dailyReportManager.getAverageMedicationTaken(patient_id);
        model.addAttribute("Averagemedicationtaken",average_medication);
        String name= (String) session.getAttribute("name");
        Patient patient= patientManager1.getPatientbyID(patient_id);
        model.addAttribute("pname",patient.getName());
        model.addAttribute("patient_id",patient.getId());
        model.addAttribute("name", name);
        model.addAttribute("daysvalues", dailyReportManager.getDaysValues(patient_id));
        model.addAttribute("dayslabels", dailyReportManager.getDaysLabel(patient_id));
        model.addAttribute("weeksvalues", dailyReportManager.getWeekValues(patient_id));
        model.addAttribute("weekslabels", dailyReportManager.getWeekLabels(patient_id));
        model.addAttribute("monthsvalues", dailyReportManager.getMonthValues(patient_id));
        model.addAttribute("monthslabels", dailyReportManager.getMonthLabels(patient_id));
        return "progress";

    }

    @GetMapping("/configure_parameters")
    public String configureParameters(Model model, @RequestParam("patient_id") int patient_id) {
        MonitoringParameters mp= monitoringParameters.getParametersbyPatientId(patient_id);
        model.addAttribute("monitoringParameters", mp);
        return "configure_parameters";
    }
    @GetMapping("/patient_alerts")
    public String patientAlerts(Model model, @RequestParam("patient_id") int patient_id,HttpSession session) {
        List<Alerts>alertsList=alertsManager.getAllAlerts(patient_id);
        List<Alerts>alertsactive=alertsManager.getAllActiveAlerts(patient_id);
        List<Alerts>alertsresolved=alertsManager.getAllResolvedAlerts(patient_id);
        model.addAttribute("alertsList", alertsList);
        String name= (String) session.getAttribute("name");
        Patient patient= patientManager1.getPatientbyID(patient_id);
        model.addAttribute("pname", patient.getName());
        model.addAttribute("patient_id", patient.getId());
        model.addAttribute("name", name);
        model.addAttribute("totalalerts",alertsList.size());
        model.addAttribute("alertsactive", alertsactive.size());
        model.addAttribute("resolvedalerts",alertsresolved.size());
        return "patient_alerts";
    }

}
