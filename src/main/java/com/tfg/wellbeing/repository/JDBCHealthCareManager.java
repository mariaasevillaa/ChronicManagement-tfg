package com.tfg.wellbeing.repository;

import com.tfg.wellbeing.model.Health_professional;
import com.tfg.wellbeing.model.Patient;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JDBCHealthCareManager {
    private final DataSource ds;
    public JDBCHealthCareManager(DataSource ds) {
        this.ds = ds;
    }

    public Health_professional getHealthProfessionalByEmail(String email) {
        String sql = "SELECT hp.* " +
                "FROM health_professional hp " +
                "INNER JOIN users u ON hp.user_id = u.id " +
                "WHERE u.email = ?";

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Health_professional healthProfessional= new Health_professional(rs.getInt("id"),rs.getInt("user_id"),rs.getString("name"),rs.getString("surname"),rs.getString("speciality"));
                    return healthProfessional;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    }

