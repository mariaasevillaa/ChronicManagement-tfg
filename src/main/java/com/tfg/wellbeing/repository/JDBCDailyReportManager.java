package com.tfg.wellbeing.repository;

import com.tfg.wellbeing.model.Daily_report;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JDBCDailyReportManager {
    private final DataSource datasource;

    public JDBCDailyReportManager(DataSource datasource) {
        this.datasource = datasource;
    }

    public int  addDailyReport(int patient_id,int mood, int medication_taken,String note,String date){
       String sql= "INSERT INTO daily_reports (patient_id,mood,medication_taken,notes,date) VALUES(?,?,?,?,?)";
        try(Connection c= datasource.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,patient_id);
            ps.setInt(2,mood);
            ps.setInt(3,medication_taken);
            ps.setString(4,note);
            ps.setString(5,date);
            ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                int id = rs.getInt(1); //to get the id from the dialy report
                return id;
            }
    }catch (SQLException e){
            throw new RuntimeException(e);
        }

        }
        public List<Daily_report> getReportByPatientId(int patient_id) {
            String sql = "SELECT * FROM daily_reports WHERE patient_id= ? ORDER BY date DESC ";
            List<Daily_report> daily_reports = new ArrayList<>();
            try (Connection c = datasource.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setInt(1,patient_id);
               try(ResultSet rs = ps.executeQuery()){
                   while(rs.next()){
                       Daily_report report= new Daily_report(rs.getInt("id"),rs.getInt("patient_id"),rs.getInt("mood"),rs.getInt("medication_taken"),rs.getString("notes"),rs.getString("date"));
                       daily_reports.add(report);
                   }
                   return daily_reports;
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        public int getTotalReportsByPatientId(int patient_id) {
        String sql ="SELECT COUNT(*) FROM daily_reports WHERE patient_id= ? ";
        try(Connection c = datasource.getConnection();
        PreparedStatement ps =c.prepareStatement(sql)) {
            ps.setInt(1,patient_id);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;

        }

        public double getAverageMood(int patient_id) {
        String sql = "SELECT AVG(mood) FROM daily_reports WHERE patient_id= ? ";
        try (Connection c = datasource.getConnection();
        PreparedStatement ps= c.prepareStatement(sql)) {
            ps.setInt(1,patient_id);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    return rs.getDouble(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
        }
        public double getAverageMedicationTaken(int patient_id) {
        String sql = "SELECT (SUM (medication_taken)*100 )/COUNT(*) FROM daily_reports WHERE patient_id= ?";
            try (Connection c = datasource.getConnection();
                 PreparedStatement ps= c.prepareStatement(sql)) {
                ps.setInt(1,patient_id);
                try(ResultSet rs = ps.executeQuery()){
                    while (rs.next()) {
                        return rs.getDouble(1);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return 0;
        }

}
