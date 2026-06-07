package com.tfg.wellbeing.repository;

import com.tfg.wellbeing.model.Achievement;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JDBCAchievementManager {
    private final DataSource dataSource;
    public JDBCAchievementManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public List<Achievement> getAchievements() {
        String sql= "SELECT * FROM achievements ";
        List<Achievement> achievementsList = new ArrayList<>();
        try(Connection c = dataSource.getConnection();
        PreparedStatement ps= c.prepareStatement(sql)) {
            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    Achievement achievement = new Achievement(rs.getInt("id"),rs.getString("name"),rs.getString("description"),rs.getInt("points_reward"),rs.getInt("reports_needed"));
                    achievementsList.add(achievement);
                }
            }
            return achievementsList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
