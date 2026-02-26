package com.tfg.wellbeing.model;

public class Hp_Patient {
    private int patient_id;
    private int hp_id;

    public Hp_Patient(int patient_id, int hp_id) {
        this.patient_id = patient_id;
        this.hp_id = hp_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getHp_id() {
        return hp_id;
    }

    public void setHp_id(int hp_id) {
        this.hp_id = hp_id;
    }

    @Override
    public String toString() {
        return "Hp_Patient{" +
                "patient_id=" + patient_id +
                ", hp_id=" + hp_id +
                '}';
    }
}
