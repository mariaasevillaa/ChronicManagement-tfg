package com.tfg.wellbeing.model;


import java.sql.Date;

public class Daily_report {
    private int id;
    private int patient_id;
    private int mood;
    private int medication_taken;
    private String note;
    private String report_date;

    public Daily_report(int id, int patient_id, int mood, int medication_taken, String note,String report_date) {
        this.id = id;
        this.patient_id = patient_id;
        this.mood = mood;
        this.medication_taken = medication_taken;
        this.note = note;
        this.report_date = report_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public int getMedication_taken() {
        return medication_taken;
    }

    public void setMedication_taken(int medication_taken) {
        this.medication_taken = medication_taken;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getReport_date() {
        return report_date;
    }

    public void setReport_date(String report_date) {
        this.report_date = report_date;
    }

    @Override
    public String toString() {
        return "Daily_report{" +
                "id=" + id +
                ", patient_id=" + patient_id +
                ", mood=" + mood +
                ", medication_taken=" + medication_taken +
                ", note='" + note + '\'' +
                ", report_date=" + report_date +
                '}';
    }
}
