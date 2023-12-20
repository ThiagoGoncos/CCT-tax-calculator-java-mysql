/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

import static taxcalculatorapp.DatabaseSetup.DB_URL;
import static taxcalculatorapp.DatabaseSetup.PASSWORD;
import static taxcalculatorapp.DatabaseSetup.TABLE_NAME;
import static taxcalculatorapp.DatabaseSetup.USER;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
@author ThiagoGoncos (2022161) and KelvinDumas (2022264)
GitHub Link: https://github.com/Thiago2022161/taxcalculatorapp
Presentation Video Link: https://www.youtube.com/watch?v=Fmmwt0PodRk
*/

// Definition of a class responsible for writing user data and taxes to the database
public class DatabaseWriter extends Database {

    // Method to add a new user to the database
    public boolean addUser(User user) {
        try (
                // Establish a database connection
                 Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD); // Prepare a SQL statement to check if the user already exists
                  PreparedStatement checkUserStmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE username = ?"); // Prepare a SQL statement to insert a new user into the RegularUsers table
                  PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO " + TABLE_NAME + " (username, password, name, surname, jobRole, userType) VALUES (?, ?, ?, ?, ?, ?)"
                );) {
            // Set parameters in the checkUserStmt SQL statement
            checkUserStmt.setString(1, user.getUsername());

            // Execute the SQL statement to check if the user already exists
            ResultSet existingUser = checkUserStmt.executeQuery();

            // If the user already exists, print a message and return false
            if (existingUser.next()) {
                System.out.println("User already exists!");
                return false;
            }

            // Set parameters in the stmt SQL statement for user insertion
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getSurname());
            stmt.setString(5, ((RegularUser) user).getJobRole());
            stmt.setString(6, user.getUserType().toString());

            // Execute the SQL statement to insert the new user
            stmt.executeUpdate();

            // Return true to indicate successful user addition
            return true;
        } catch (SQLException e) {
            // Return false in case of SQL exception during user addition
            return false;
        }
    }

    // Method to save user data and taxes to the database
    public static boolean saveUserDataAndTaxes(RegularUser regularUser, double grossIncome, double taxCredits,
            double incomeTax, double usc, double prsi) {
        try (
                // Establish a database connection
                 Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD); // Prepare a SQL statement to insert user data into the RegularUsers table
                  PreparedStatement userStmt = conn.prepareStatement(
                        "INSERT INTO " + TABLE_NAME + " (username, password, name, surname, jobRole, userType) VALUES (?, ?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                ); // Prepare a SQL statement to insert tax data into the TaxTable
                  PreparedStatement taxStmt = conn.prepareStatement(
                        "INSERT INTO TaxTable (username, grossIncome, taxCredits, incomeTax, usc, prsi) VALUES (?, ?, ?, ?, ?, ?)"
                );) {
            // Set auto-commit to false to enable a transaction
            conn.setAutoCommit(false);

            // Set parameters in the userStmt SQL statement for user data insertion
            userStmt.setString(1, regularUser.getUsername());
            userStmt.setString(2, regularUser.getPassword());
            userStmt.setString(3, regularUser.getName());
            userStmt.setString(4, regularUser.getSurname());
            userStmt.setString(5, regularUser.getJobRole());
            userStmt.setString(6, regularUser.getUserType().toString());

            // Execute the SQL statement to insert user data
            int affectedRows = userStmt.executeUpdate();

            // If no rows are affected, throw an exception
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            // Obtain generated keys (if any)
            try ( ResultSet generatedKeys = userStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Set parameters in the taxStmt SQL statement for tax data insertion
                    taxStmt.setString(1, regularUser.getUsername());
                    taxStmt.setDouble(2, grossIncome);
                    taxStmt.setDouble(3, taxCredits);
                    taxStmt.setDouble(4, incomeTax);
                    taxStmt.setDouble(5, usc);
                    taxStmt.setDouble(6, prsi);

                    // Execute the SQL statement to insert tax data
                    taxStmt.executeUpdate();

                    // Store the user in the database
                    Database.storeUser(regularUser);

                    // Commit the transaction and set auto-commit to true
                    conn.commit();
                    conn.setAutoCommit(true);

                    // Print a success message and return true
                    System.out.println("User data and taxes saved successfully!");
                    return true;
                } else {
                    // Throw an exception if no ID is obtained
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            // Return false in case of SQL exception during data and tax saving
            return false;
        }
    }
}
