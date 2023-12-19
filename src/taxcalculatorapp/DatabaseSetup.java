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

public class DatabaseSetup {

    protected static final String DB_BASE_URL = "jdbc:mysql://localhost:3306";
    protected static final String USER = "ooc2023";
    protected static final String PASSWORD = "ooc2023";
    protected static final String DB_NAME = "Taxes";
    protected static final String TABLE_NAME = "RegularUsers";
    protected static final String DB_URL = DB_BASE_URL + "/" + DB_NAME;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }
    }

    public static void setupDatabase() {
        try ( Connection conn = DriverManager.getConnection(DB_BASE_URL, USER, PASSWORD)) {
            try ( Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
                System.out.println("Database '" + DB_NAME + "' created successfully.");
            }

            try ( Statement stmt = conn.createStatement()) {
                stmt.execute("USE " + DB_NAME);
            }

            createTable();
        } catch (SQLException e) {
        }
    }

    public static void createTable() {
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
        ) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "username VARCHAR(50) NOT NULL,"
                + "password VARCHAR(50) NOT NULL,"
                + "name VARCHAR(50),"
                + "surname VARCHAR(50),"
                + "jobRole VARCHAR(50),"
                + "userType VARCHAR(20) NOT NULL)";
            executeUpdate(conn, createTableSQL);

            String createTaxTableSQL = "CREATE TABLE IF NOT EXISTS TaxTable ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "username VARCHAR(50) NOT NULL,"
                + "grossIncome DOUBLE NOT NULL,"
                + "taxCredits DOUBLE NOT NULL,"
                + "incomeTax DOUBLE NOT NULL,"
                + "usc DOUBLE NOT NULL,"
                + "prsi DOUBLE NOT NULL)";
            executeUpdate(conn, createTaxTableSQL);

            System.out.println("Table '" + TABLE_NAME + "' created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();  // Trate a exceção de forma adequada
        }
    }

    private static void executeUpdate(Connection conn, String sql) {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();  // Trate a exceção de forma adequada
        }
    }
}