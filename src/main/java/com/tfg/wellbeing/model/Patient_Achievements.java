package com.tfg.wellbeing.model;

public class Patient_Achievements {
    private int patient_id;
    private int achievement_id;

    public Patient_Achievements(int patient_id, int achievement_id) {
        this.patient_id = patient_id;
        this.achievement_id = achievement_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getAchievement_id() {
        return achievement_id;
    }

    public void setAchievement_id(int achievement_id) {
        this.achievement_id = achievement_id;
    }

    @Override
    public String toString() {
        return "Patient_Achievements{" +
                "patient_id=" + patient_id +
                ", achievement_id=" + achievement_id +
                '}';
    }

}
