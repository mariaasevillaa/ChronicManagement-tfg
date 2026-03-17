package com.tfg.wellbeing.repository;

import com.tfg.wellbeing.model.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JDBCUserManager {
    private final DataSource dataSource;
    public JDBCUserManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public int addUser(String email, String password, String role) {
        String sql = "INSERT INTO users (email, password, role) values (?, ?, ?)";
        try(Connection c= dataSource.getConnection();
            PreparedStatement ps= c.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, role);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new RuntimeException("No ID obtained");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error adding user", e);
        }

    }
    public int existsUser(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try(Connection c= dataSource.getConnection();
            PreparedStatement ps= c.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email", e);
        }
        return 1;
    }
    public User getUserByEmail(String email) {

        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("role")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


}
