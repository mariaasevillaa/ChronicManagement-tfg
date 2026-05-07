package com.tfg.wellbeing.repository;

import com.tfg.wellbeing.model.Alerts;
import com.tfg.wellbeing.model.Patient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JDBCAlertsManager {
    private final DataSource dataSource;
    private final JDBCDailyReportManager dailyReportManager;
    private final JDBCMonitoringParameters monitoringParameters;
    private JDBCPatientManager patientManager;
    public JDBCAlertsManager(DataSource dataSource, JDBCDailyReportManager dailyReportManager, JDBCMonitoringParameters monitoringParameters,JDBCPatientManager patientManager) {
        this.dataSource = dataSource;
        this.dailyReportManager = dailyReportManager;
        this.monitoringParameters = monitoringParameters;
        this.patientManager = patientManager;
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
 public void createAlerts (int patient_id, String type, int resolved,String message,String date) {
        String sql="INSERT INTO alerts (patient_id,message, date, resolved,type) VALUES (?,?,?,0,?)";
        try (Connection c = dataSource.getConnection();
        PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,patient_id);
            ps.setString(2,message);
            ps.setString(3,date);
            ps.setString(4,type);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

 }
 public List<Alerts> getAllAlerts (int patient_id) {
        String sql="SELECT * FROM alerts WHERE patient_id=? ";
        List<Alerts> alerts = new ArrayList<Alerts>();
        try(Connection c = dataSource.getConnection();
        PreparedStatement ps =c.prepareStatement(sql)) {
            ps.setInt(1,patient_id);
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Alerts alert = new Alerts (rs.getInt("id"),rs.getInt("patient_id"),rs.getString("message"),rs.getString("date"),rs.getInt("resolved"),rs.getString("type"));
                    alerts.add(alert);
                }
            }
            return alerts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
 }
    public List<Alerts> getAllActiveAlerts (int patient_id) {
        String sql="SELECT * FROM alerts WHERE patient_id=? AND resolved=0";
        List<Alerts> alerts = new ArrayList<Alerts>();
        try(Connection c = dataSource.getConnection();
            PreparedStatement ps =c.prepareStatement(sql)) {
            ps.setInt(1,patient_id);
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Alerts alert = new Alerts (rs.getInt("id"),rs.getInt("patient_id"),rs.getString("message"),rs.getString("date"),rs.getInt("resolved"),rs.getString("type"));
                    alerts.add(alert);
                }
            }
            return alerts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Alerts> getAllResolvedAlerts (int patient_id) {
        String sql="SELECT * FROM alerts WHERE patient_id=? AND resolved=1";
        List<Alerts> alerts = new ArrayList<Alerts>();
        try(Connection c = dataSource.getConnection();
            PreparedStatement ps =c.prepareStatement(sql)) {
            ps.setInt(1,patient_id);
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Alerts alert = new Alerts (rs.getInt("id"),rs.getInt("patient_id"),rs.getString("message"),rs.getString("date"),rs.getInt("resolved"),rs.getString("type"));
                    alerts.add(alert);
                }
            }
            return alerts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void checkMissedReportsAlert(int patient_id) {
        int daysWithoutReports = dailyReportManager.getDaysSinceLastReport(patient_id);
        int missedReportsDays = monitoringParameters.getMissedReportsDays(patient_id);

        if (daysWithoutReports==-1 || daysWithoutReports >= missedReportsDays) {
            if (!hasActiveAlerts(patient_id, "MISSED_REPORT")) {
                String message;
                if (daysWithoutReports == -1) {
                    message = "Patient has not submitted any reports yet";
                } else {
                    message = "Patient has not submitted daily reports for " + daysWithoutReports + " days";
                }
                createAlerts(
                        patient_id,
                        "MISSED_REPORT",
                        0,
                        message,
                        LocalDate.now().toString()
                );
            }
        }
    }
    @Scheduled(cron = "0 0 9 * * *")
    public void checkAllPatientsMissedReports() {
        List<Patient> patients = patientManager.getAllPatients();

        for (Patient p : patients) {
            checkMissedReportsAlert(p.getId());
        }
    }

    public void resolveAlertForReports(int patient_id, String type){
        String sql="UPDATE alerts SET resolved=1 WHERE patient_id=? AND type=? AND resolved=0";
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, patient_id);
            ps.setString(2,type);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void resolveAlert(int alert_id){
        String sql="UPDATE alerts SET resolved=1 WHERE id=?";
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, alert_id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
