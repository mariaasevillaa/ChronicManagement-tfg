package com.tfg.wellbeing.repository;

import com.tfg.wellbeing.model.Patient;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JDBCHpPatientManager {
    private final DataSource dataSource;
    public JDBCHpPatientManager(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    public List<Patient> getPatientByHpId(int hp_id){
        String sql ="SELECT p. * FROM  hp_patient hp JOIN patient p ON p.id=hp.patient_id WHERE hp.hp_id= ? ORDER BY p.surname, p.name";
        List<Patient> patients = new ArrayList<>();
        try(Connection c= dataSource.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, hp_id);
           try(ResultSet rs = ps.executeQuery()){
               while(rs.next()){
                   Patient patient= new Patient(rs.getInt("id"),
                           rs.getInt("user_id"),
                           rs.getString("name"),
                           rs.getString("surname"),
                           rs.getString("date_of_birth"),
                           rs.getString("chronic_condition"),
                           rs.getString("diagnosis_date")
                           );
                   patients.add(patient);
               }
               return patients;
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void assignPatientToHp(int hpId, int patientId) {
        String sql = "INSERT OR IGNORE INTO hp_patient (hp_id, patient_id) VALUES (?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, hpId);
            stmt.setInt(2, patientId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
