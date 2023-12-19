/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

/**
 *
 * @author thiagogoncos
 */
import static testetrabalhosam.DatabaseSetup.DB_URL;
import static testetrabalhosam.DatabaseSetup.PASSWORD;
import static testetrabalhosam.DatabaseSetup.TABLE_NAME;
import static testetrabalhosam.DatabaseSetup.USER;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseWriter extends Database {

    public boolean addUser(User user) {
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement checkUserStmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE username = ?");
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO " + TABLE_NAME + " (username, password, name, surname, jobRole, userType) VALUES (?, ?, ?, ?, ?, ?)"
            );
        ) {
            checkUserStmt.setString(1, user.getUsername());
            ResultSet existingUser = checkUserStmt.executeQuery();

            if (existingUser.next()) {
                System.out.println("User already exists!");
                return false;
            }

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getSurname());
            stmt.setString(5, ((RegularUser) user).getJobRole());
            stmt.setString(6, user.getUserType().toString());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }


    public static boolean saveUserDataAndTaxes(RegularUser regularUser, double grossIncome, double taxCredits,
            double incomeTax, double usc, double prsi) {
        try (
                 Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);  PreparedStatement userStmt = conn.prepareStatement(
                "INSERT INTO " + TABLE_NAME + " (username, password, name, surname, jobRole, userType) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );  PreparedStatement taxStmt = conn.prepareStatement(
                "INSERT INTO TaxTable (username, grossIncome, taxCredits, incomeTax, usc, prsi) VALUES (?, ?, ?, ?, ?, ?)"
        );) {
            conn.setAutoCommit(false);

            userStmt.setString(1, regularUser.getUsername());
            userStmt.setString(2, regularUser.getPassword());
            userStmt.setString(3, regularUser.getName());
            userStmt.setString(4, regularUser.getSurname());
            userStmt.setString(5, regularUser.getJobRole());
            userStmt.setString(6, regularUser.getUserType().toString());

            int affectedRows = userStmt.executeUpdate();
    if (affectedRows == 0) {
        throw new SQLException("Creating user failed, no rows affected.");
    }

    try (ResultSet generatedKeys = userStmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {

                    taxStmt.setString(1, regularUser.getUsername());
                    taxStmt.setDouble(2, grossIncome);
                    taxStmt.setDouble(3, taxCredits);
                    taxStmt.setDouble(4, incomeTax);
                    taxStmt.setDouble(5, usc);
                    taxStmt.setDouble(6, prsi);

                    taxStmt.executeUpdate();
                    
                    Database.storeUser(regularUser);

                    conn.commit();
                    conn.setAutoCommit(true);

                    System.out.println("User data and taxes saved successfully!");
                    return true;
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            return false;
        }
    }
}
