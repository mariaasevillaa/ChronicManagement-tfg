package com.tfg.wellbeing.repository;

import com.tfg.wellbeing.model.MonitoringParameters;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JDBCMonitoringParameters {
    private final DataSource dataSource;
    public JDBCMonitoringParameters(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public MonitoringParameters getParametersbyPatientId(int patient_id) {
        String sql = "SELECT * FROM monitoring_parameters WHERE patient_id = ?";
        try(Connection c = dataSource.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, patient_id);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    MonitoringParameters monitoringParameters = new MonitoringParameters(rs.getInt("id"),rs.getInt("patient_id"),rs.getInt("mood_threshold"),rs.getInt("missed_medication_days"),rs.getInt("missed_reports_days"));
                    return monitoringParameters;
                }


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;


    }
    public void setMonitoringParameters(int patient_id, int mood_threshold, int missed_reports_days, int medication_missed_days) {

        String checkSql = "SELECT id FROM monitoring_parameters WHERE patient_id = ?";

        try (Connection c = dataSource.getConnection();
             PreparedStatement check = c.prepareStatement(checkSql)) {

            check.setInt(1, patient_id);

            try (ResultSet rs = check.executeQuery()) {

                if (rs.next()) {

                    // UPDATE si ya existe
                    String updateSql = "UPDATE monitoring_parameters SET mood_threshold = ?, missed_medication_days = ?,missed_reports_days = ? WHERE patient_id = ?";

                    try (PreparedStatement update = c.prepareStatement(updateSql)) {

                        update.setInt(1, mood_threshold);
                        update.setInt(2, missed_reports_days);
                        update.setInt(3, medication_missed_days);
                        update.setInt(4, patient_id);

                        update.executeUpdate();
                    }

                } else {

                    // INSERT si no existe
                    String insertSql = "INSERT INTO monitoring_parameters (patient_id, mood_threshold, missed_medication_days, missed_reports_days) VALUES (?, ?, ?, ?)";

                    try (PreparedStatement insert = c.prepareStatement(insertSql)) {

                        insert.setInt(1, patient_id);
                        insert.setInt(2, mood_threshold);
                        insert.setInt(3, missed_reports_days);
                        insert.setInt(4, medication_missed_days);

                        insert.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int getMissedReportsDays(int patient_id) {
        String sql = "SELECT missed_reports_days FROM monitoring_parameters WHERE patient_id = ?";

        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, patient_id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("missed_reports_days");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 2;
    }

}
