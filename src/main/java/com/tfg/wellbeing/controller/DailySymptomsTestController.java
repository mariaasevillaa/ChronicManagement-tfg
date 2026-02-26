package com.tfg.wellbeing.controller;

import com.tfg.wellbeing.repository.JDBCDailyReportManager;
import com.tfg.wellbeing.repository.JDBCDailySymptomsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DailySymptomsTestController {

    private final JDBCDailySymptomsManager manager;
    private final JDBCDailyReportManager reportManager;

    public DailySymptomsTestController(JDBCDailySymptomsManager manager, JDBCDailyReportManager reportManager) {
        this.manager = manager;
        this.reportManager = reportManager;
    }

    @GetMapping("/test/add-symptom")
    public String addSymptom(@RequestParam int reportId, @RequestParam int symptomId) {
        manager.addDailySymptoms(reportId, symptomId);
        return "Inserted into daily_symptoms table";
    }
    @GetMapping("/test/add-full-report")
    public String addFullReport(@RequestParam int patientId,
                                @RequestParam int mood,
                                @RequestParam int medication,
                                @RequestParam String notes,
                                @RequestParam String date,
                                @RequestParam List<Integer> symptoms) {

        int reportId = reportManager.addDailyReport(patientId, mood, medication, notes, date);

        for (Integer symptomId : symptoms) {
            manager.addDailySymptoms(symptomId,reportId);
        }

        return "Report + symptoms inserted. Report ID: " + reportId;
    }
}