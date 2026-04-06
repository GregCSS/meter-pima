// Database queries for Staff CRUD on signup/login
package dao;

import java.sql.*;
import java.util.*;
import model.Staff;

public class StaffDAO extends BaseDAO implements DAO<Staff> {

    // Create a staff
    @Override
    public void create(Staff staff) {
        try (Connection conn = getConnection();
            // Prepare INSERT DML statement
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO staff(name, password) VALUES(?, ?)")) {
            // Get staff name and hashed password
            stmt.setString(1, staff.getName());
            stmt.setString(2, staff.getPassword());
            // Execute the statement
            stmt.executeUpdate();
        // Handle error(s)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Return a specific staff by ID
    @Override
    public Staff getById(int id) {
        try (Connection conn = getConnection();
            // Prepare SELECT DML statement
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM staff WHERE id = ?")) {
            // Get staff id
            stmt.setInt(1, id);
            // Execute the statement
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) { // Access the first row
                // Get the staff details
                return new Staff(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("password")
                );
            }
        // Handle error(s)
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Return all staffs
    @Override
    public List<Staff> getAll() {
        List<Staff> staffs = new ArrayList<>(); // Composition

        try (Connection conn = getConnection();
            // Create a static SELECT DML statement
            Statement stmt = conn.createStatement()) {
            // Execute the statement
            ResultSet rs = stmt.executeQuery("SELECT * FROM staff");

            while (rs.next()) { // Access the first row
                staffs.add(new Staff(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("password")
                ));
            }
        // Handle error(s)
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return the staff list
        return staffs;
    }

    // Update name/password for a specific staff
    @Override
    public void update(Staff staff) {
        try (Connection conn = getConnection();
            // Prepare UPDATE DML statement
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE staff SET name = ?, password = ? WHERE id = ?")) {
            // Get staff name, hashed password and id
            stmt.setString(1, staff.getName());
            stmt.setString(2, staff.getPassword());
            stmt.setInt(3, staff.getId());
            // Execute the statement
            stmt.executeUpdate();
        // Handle error(s)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Remove a staff by ID
    @Override
    public void delete(int id) {
        try (Connection conn = getConnection();
            // Prepare UPDATE DML statement
            PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM staff WHERE id = ?")) {
            // Get staff id
            stmt.setInt(1, id);
            // Execute the statement
            stmt.executeUpdate();
        // Handle error(s)
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
}
