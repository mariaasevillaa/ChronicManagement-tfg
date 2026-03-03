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
    public List<Achievements> getAchievements() {
        String sql= "SELECT * FROM achievements";
        List<Achievements> achievementsList = new ArrayList<>();
        try(Connection c = dataSource.getConnection();
        PreparedStatement ps= c.prepareStatement(sql)) {
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
