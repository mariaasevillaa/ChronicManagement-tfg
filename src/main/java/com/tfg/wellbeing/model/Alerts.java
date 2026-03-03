package com.tfg.wellbeing.model;

public class Alerts {
    private int id;
    private int patient_id;
    private String message;
    private String date;
    private int resolved;
    private String type;

    public Alerts(int id, int patient_id, String message, String date, int resolved, String type) {
        this.id = id;
        this.patient_id = patient_id;
        this.message = message;
        this.date = date;
        this.resolved = resolved;
        this.type = type;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getResolved() {
        return resolved;
    }

    public void setResolved(int resolved) {
        this.resolved = resolved;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Alerts{" +
                "id=" + id +
                ", patient_id=" + patient_id +
                ", message='" + message + '\'' +
                ", date='" + date + '\'' +
                ", resolved=" + resolved +
                ", type='" + type + '\'' +
                '}';
    }
}
