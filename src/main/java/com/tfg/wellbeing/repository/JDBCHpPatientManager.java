package com.tfg.wellbeing.repository;

import com.tfg.wellbeing.model.Patient;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JDBCHpPatientManager {
    private final DataSource dataSource;
    public JDBCHpPatientManager(DataSource dataSource) {

        this.dataSource = dataSource;
    }


    public void assignPatientToHp(int hpId, int patientId) {
        String sql = "INSERT OR IGNORE INTO hp_patient (hp_id, patient_id) VALUES (?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, hpId);
            stmt.setInt(2, patientId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
