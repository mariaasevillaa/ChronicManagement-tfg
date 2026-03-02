package com.tfg.wellbeing.repository;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class JDBCPatientAchievements {
    private final DataSource dataSource;

    public JDBCPatientAchievements(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public void addPatientAchievements(int patient_id, int achievement_id) {
        String sql ="INSERT INTO patient_achievements (patient_id, achievement_id) VALUES (?,?)";
        try(Connection c= dataSource.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, patient_id);
            ps.setInt(2, achievement_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
