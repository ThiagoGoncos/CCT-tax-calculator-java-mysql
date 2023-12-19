/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

/**
 *
 * @author thiagogoncos
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// Definition of a class responsible for setting up the database schema and tables
public class DatabaseSetup {

    // Database connection parameters
    protected static final String DB_BASE_URL = "jdbc:mysql://localhost:3306";
    protected static final String USER = "ooc2023";
    protected static final String PASSWORD = "ooc2023";
    protected static final String DB_NAME = "Taxes";
    protected static final String TABLE_NAME = "RegularUsers";
    protected static final String DB_URL = DB_BASE_URL + "/" + DB_NAME;

    // Static block to load the MySQL JDBC driver during class initialization
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Handle exception if the driver class is not found
        }
    }

    // Method to set up the database, create it if not exists, and create required tables
    public static void setupDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_BASE_URL, USER, PASSWORD)) {
            // Attempt to create the database if it does not exist
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
                System.out.println("Database '" + DB_NAME + "' created successfully.");
            }

            // Switch to using the created database
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("USE " + DB_NAME);
            }

            // Create tables for RegularUsers and TaxTable
            createTable();
        } catch (SQLException e) {
            // Handle any SQL-related exceptions
        }
    }

    // Method to create tables in the database
    public static void createTable() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            // SQL statement to create RegularUsers table with specific columns
            String createTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "username VARCHAR(50) NOT NULL,"
                    + "password VARCHAR(50) NOT NULL,"
                    + "name VARCHAR(50),"
                    + "surname VARCHAR(50),"
                    + "jobRole VARCHAR(50),"
                    + "userType VARCHAR(20) NOT NULL)";
            // Execute the SQL statement to create the RegularUsers table
            executeUpdate(conn, createTableSQL);

            // SQL statement to create TaxTable with specific columns
            String createTaxTableSQL = "CREATE TABLE IF NOT EXISTS TaxTable ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "username VARCHAR(50) NOT NULL,"
                    + "grossIncome DOUBLE NOT NULL,"
                    + "taxCredits DOUBLE NOT NULL,"
                    + "incomeTax DOUBLE NOT NULL,"
                    + "usc DOUBLE NOT NULL,"
                    + "prsi DOUBLE NOT NULL)";
            // Execute the SQL statement to create the TaxTable
            executeUpdate(conn, createTaxTableSQL);

            System.out.println("Table '" + TABLE_NAME + "' created successfully.");
        } catch (SQLException e) {
            // Handle any SQL-related exceptions
        }
    }

    // Method to execute an SQL update statement
    private static void executeUpdate(Connection conn, String sql) {
        try (Statement stmt = conn.createStatement()) {
            // Execute the SQL update statement
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // Handle any SQL-related exceptions
        }
    }
}