package com.tfg.wellbeing.repository;

import javax.sql.DataSource;

public class JDBCSymptomsManager {
    private final DataSource dataSource;
    public JDBCSymptomsManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public void addSymptom (String symptom){

    }
}
