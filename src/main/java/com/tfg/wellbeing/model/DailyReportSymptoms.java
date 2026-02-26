package com.tfg.wellbeing.model;

public class DailyReportSymptoms {
    private int daily_id;
    private int symptom_id;

    public DailyReportSymptoms(int daily_id, int symptom_id) {
        this.daily_id = daily_id;
        this.symptom_id = symptom_id;
    }

    public int getDaily_id() {
        return daily_id;
    }

    public void setDaily_id(int daily_id) {
        this.daily_id = daily_id;
    }

    public int getSymptom_id() {
        return symptom_id;
    }

    public void setSymptom_id(int symptom_id) {
        this.symptom_id = symptom_id;
    }

    @Override
    public String toString() {
        return "Daily_symptoms{" +
                "daily_id=" + daily_id +
                ", symptom_id=" + symptom_id +
                '}';
    }
}
