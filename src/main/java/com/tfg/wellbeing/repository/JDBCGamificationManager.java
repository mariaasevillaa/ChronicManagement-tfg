package com.tfg.wellbeing.repository;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JDBCGamificationManager {
    private final DataSource dataSource;
    public JDBCGamificationManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public void createIfNotExists(int patientId) {
        String checkSql = "SELECT COUNT(*) FROM gamification_status WHERE patient_id = ?";

        try (Connection c = dataSource.getConnection();
             PreparedStatement check = c.prepareStatement(checkSql)) {

            check.setInt(1, patientId);

            int count;
            try (ResultSet rs = check.executeQuery()) {
                rs.next();
                count = rs.getInt(1);
            }

            if (count == 0) {
                String insertSql = "INSERT INTO gamification_status (patient_id, points) VALUES (?, 0)";
                try (PreparedStatement ins = c.prepareStatement(insertSql)) {
                    ins.setInt(1, patientId);
                    ins.executeUpdate();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error creating gamification_status", e);
        }
    }

    public int getPoints (int patient_id){
        String sql = "SELECT points FROM gamification_status WHERE patient_id = ?";
        try(Connection c =dataSource.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, patient_id);
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("points");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    // no me funciona el update

    public int addPoints(int patient_id, int points){
        createIfNotExists(patient_id);
        String sql ="UPDATE  gamification_status SET points = points= + ? WHERE patient_id = ?";
        try(Connection c = dataSource.getConnection();
        PreparedStatement ps= c.prepareStatement(sql)) {
            ps.setInt(1, points);
            ps.setInt(2, patient_id);
             int rows = ps.executeUpdate();

            if (rows == 0) {
                throw new RuntimeException("No rows updated. patient_id not found: " + patient_id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error updating points",e);
        }
        return getPoints(patient_id);
    }

}
