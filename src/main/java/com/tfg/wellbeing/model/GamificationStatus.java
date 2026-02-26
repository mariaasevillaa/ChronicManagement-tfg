package com.tfg.wellbeing.model;

public class GamificationStatus {
    private int id;
    private int patient_id;
    private int points;
    private int streak_days;

    public GamificationStatus(int id, int patient_id, int points, int streak_days) {
        this.id = id;
        this.patient_id = patient_id;
        this.points = points;
        this.streak_days = streak_days;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getStreak_days() {
        return streak_days;
    }

    public void setStreak_days(int streak_days) {
        this.streak_days = streak_days;
    }

    @Override
    public String toString() {
        return "GamificationStatus{" +
                "id=" + id +
                ", patient_id=" + patient_id +
                ", points=" + points +
                ", streak_days=" + streak_days +
                '}';
    }
}
