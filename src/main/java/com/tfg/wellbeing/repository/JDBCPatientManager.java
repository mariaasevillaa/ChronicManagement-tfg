package com.tfg.wellbeing.repository;

import com.tfg.wellbeing.model.Daily_report;
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
public class JDBCPatientManager {
    private final DataSource dataSource;

    public JDBCPatientManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addPatient(int user_id, String name, String surname, String date_of_birth, String chronic_condition, String diagnosis_date) {
        String sql = "INSERT INTO patient(user_id,name,surname,date_of_birth,chronic_condition,diagnosis_date) VALUES(?,?,?,?,?,?)";
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, user_id);
            ps.setString(2, name);
            ps.setString(3, surname);
            ps.setString(4, date_of_birth);
            ps.setString(5, chronic_condition);
            ps.setString(6, diagnosis_date);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


        public List<Patient> getAllPatients() {
            String sql2 = "SELECT * FROM patient ";
            List<Patient> patients = new ArrayList<>();
            try (Connection c = dataSource.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql2)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Patient patient= new Patient(rs.getInt("id"),
                                rs.getInt("user_id"),
                                rs.getString("name"),
                                rs.getString("surname"),
                                rs.getString("date_of_birth"),
                               rs.getString("chronic_condition"),
                                rs.getString("diagnosis_date"));
                        patients.add(patient);
                    }
                    return patients;
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public int getPatientIDbyUserID(int user_id) {
        String sql = "SELECT id FROM patient WHERE user_id=?";
        try(Connection c = dataSource.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, user_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }


}
