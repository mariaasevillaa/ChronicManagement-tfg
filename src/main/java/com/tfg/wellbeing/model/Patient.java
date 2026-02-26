package com.tfg.wellbeing.model;

import java.sql.Date;

public class Patient {
private int id;
private int user_id;
private String name;
private String surname;
private Date date_of_birth;
private String chronic_condition;
private Date diagnosis_date;

    public Patient(int id, int user_id, String name, String surname, Date date_of_birth, String chronic_condition, Date diagnosis_date) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.surname = surname;
        this.date_of_birth = date_of_birth;
        this.chronic_condition = chronic_condition;
        this.diagnosis_date = diagnosis_date;
    }
    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public String getChronic_condition() {
        return chronic_condition;
    }

    public Date getDiagnosis_date() {
        return diagnosis_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setChronic_condition(String chronic_condition) {
        this.chronic_condition = chronic_condition;
    }

    public void setDiagnosis_date(Date diagnosis_date) {
        this.diagnosis_date = diagnosis_date;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", chronic_condition='" + chronic_condition + '\'' +
                ", diagnosis_date=" + diagnosis_date +
                '}';
    }
}
