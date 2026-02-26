package com.tfg.wellbeing.repository;

import com.tfg.wellbeing.model.Symptoms;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JDBCDailySymptomsManager {
    private final DataSource dataSource;

    public JDBCDailySymptomsManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addDailySymptoms(int symptoms_id, int daily_id) {
        String sql = "INSERT INTO daily_symptoms(daily_id,symptoms_id) VALUES(?,?)";

        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, daily_id);
            ps.setInt(2, symptoms_id);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Error inserting daily_symptoms into database", e);
        }

    }

    public List<String> getSymptomsByReportId(int report_id) {
        String sql = "SELECT s.name FROM daily_symptoms ds JOIN symptoms s ON s.id=ds.symptoms_id WHERE ds.daily_id=?";
        List<String> symptoms_name = new ArrayList<>();
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, report_id);
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    symptoms_name.add(rs.getString("name"));
                }
                return symptoms_name;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
