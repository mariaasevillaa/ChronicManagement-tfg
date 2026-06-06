package com.tfg.wellbeing.repository;

import com.tfg.wellbeing.model.Symptom;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

@Repository
public class JDBCSymptomsManager {
    private final DataSource dataSource;
    public JDBCSymptomsManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public List<Symptom> listSymptoms (){
        List<Symptom> symptoms = new ArrayList<>();
        String sql= "SELECT * FROM symptoms";
        try(Connection c = dataSource.getConnection();
            PreparedStatement pr = c.prepareStatement(sql)) {
            try{
                ResultSet rs = pr.executeQuery();
                while(rs.next()){
                    Symptom symptom = new Symptom(rs.getInt("id"),rs.getString("name"));
                    symptoms.add(symptom);
                }
                return symptoms;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
