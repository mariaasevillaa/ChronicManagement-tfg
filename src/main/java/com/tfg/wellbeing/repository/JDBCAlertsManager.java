package com.tfg.wellbeing.repository;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JDBCAlertsManager {
    private final DataSource dataSource;
    public JDBCAlertsManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }
 public boolean hasActiveAlerts (int patient_id, String type) {
        String sql= "SELECT 1 FROM alerts WHERE patient_id=? AND type=? AND resolved= 0";
        try(Connection c = dataSource.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,patient_id);
            ps.setString(2,type);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


 }

}
