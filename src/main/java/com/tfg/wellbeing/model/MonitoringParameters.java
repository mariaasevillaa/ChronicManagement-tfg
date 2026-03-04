package com.tfg.wellbeing.model;

public class MonitoringParameters {
    private int patientId;
    private int id;
    private int missed_medication_days;
    private int missed_report_days;
    private int mood_threshold;

    public MonitoringParameters(int patientId, int id, int missed_medication_days, int missed_report_days, int mood_threshold) {
        this.patientId = patientId;
        this.id = id;
        this.missed_medication_days = missed_medication_days;
        this.missed_report_days = missed_report_days;
        this.mood_threshold = mood_threshold;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMissed_medication_days() {
        return missed_medication_days;
    }

    public void setMissed_medication_days(int missed_medication_days) {
        this.missed_medication_days = missed_medication_days;
    }

    public int getMissed_report_days() {
        return missed_report_days;
    }

    public void setMissed_report_days(int missed_report_days) {
        this.missed_report_days = missed_report_days;
    }

    public int getMood_threshold() {
        return mood_threshold;
    }

    public void setMood_threshold(int mood_threshold) {
        this.mood_threshold = mood_threshold;
    }

    @Override
    public String toString() {
        return "MonitoringParameters{" +
                ", id=" + id +
                "patientId=" + patientId +
                ", missed_medication_days=" + missed_medication_days +
                ", missed_report_days=" + missed_report_days +
                ", mood_threshold=" + mood_threshold +
                '}';
    }
}
