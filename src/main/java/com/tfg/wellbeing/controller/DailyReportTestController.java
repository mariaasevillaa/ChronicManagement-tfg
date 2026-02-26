
package com.tfg.wellbeing.controller;

import com.tfg.wellbeing.model.Daily_report;
import com.tfg.wellbeing.repository.JDBCDailyReportManager;
import com.tfg.wellbeing.repository.JDBCDailySymptomsManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DailyReportTestController {

    private final JDBCDailyReportManager reportManager;
    private final JDBCDailySymptomsManager symptomsManager;

    public DailyReportTestController (JDBCDailyReportManager reportManager, JDBCDailySymptomsManager symptomsManager) {
        this.reportManager = reportManager;
        this.symptomsManager = symptomsManager;
    }

    @GetMapping("/test/patient/reports")
    public String getReports(@RequestParam int patientId) {

        List<Daily_report> reports = reportManager.getReportByPatientId(patientId);

        StringBuilder sb = new StringBuilder();
        sb.append("Patient ").append(patientId).append(" reports:\n\n");

        for (Daily_report r : reports) {
            sb.append("Report ID: ").append(r.getId())
                    .append(" | Date: ").append(r.getReport_date())
                    .append(" | Mood: ").append(r.getMood())
                    .append(" | Medication: ").append(r.getMedication_taken())
                    .append("\nNotes: ").append(r.getNote())
                    .append("\nSymptoms: ");

            List<String> symptoms = symptomsManager.getSymptomsByReportId(r.getId());
            if (symptoms.isEmpty()) sb.append("(none)");
            else sb.append(String.join(", ", symptoms));

            sb.append("\n\n-------------------------\n\n");
        }

        return sb.toString();
    }

}
