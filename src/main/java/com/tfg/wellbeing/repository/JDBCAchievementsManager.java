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
public class JDBCAchievementsManager {
    private final DataSource dataSource;
    public JDBCAchievementsManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public List<Achievements> getAchievements(int patient_id) {
        String sql= "SELECT * FROM achievements WHERE patient_id=?";
        List<Achievements> achievementsList = new ArrayList<>();
        try(Connection c = dataSource.getConnection();
        PreparedStatement ps= c.prepareStatement(sql)) {
            ps.setInt(1, patient_id);
            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    Achievements achievement = new Achievements(rs.getInt("id"),rs.getString("name"),rs.getInt("points_reward"));
                    achievementsList.add(achievement);
                }
            }
            return achievementsList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
