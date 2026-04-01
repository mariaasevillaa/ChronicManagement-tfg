package com.tfg.wellbeing.repository;

import com.tfg.wellbeing.model.Achievements;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JDBCPatientAchievements {
    private final DataSource dataSource;

    public JDBCPatientAchievements(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public boolean hasAchievement(int patientId, int achievementId) {
        String sql = "SELECT 1 FROM patient_achievements WHERE patient_id = ? AND achievement_id = ?";

        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, patientId);
            ps.setInt(2, achievementId);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    public List<Achievements> getAchievemntsbyPatientId(int patient_id) {
        String sql = "SELECT a.id, a.name, a.description,a.points_reward,a.reports_needed FROM patient_achievements pa JOIN achievements a ON a.id=pa.achievement_id WHERE pa.patient_id=?";
        List<Achievements> achievementslist = new ArrayList<>();
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, patient_id);
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Achievements achievements= new Achievements(rs.getInt("id"),rs.getString("name"),rs.getString("description"),rs.getInt("points_reward"),rs.getInt("reports_needed"));
                    achievementslist.add(achievements);

                }
                return achievementslist;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
