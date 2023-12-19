/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class Database {
    private static Map<String, User> userDatabase = new HashMap<>();

    static {
        Admin admin = new Admin("CCT", "Dublin", "Admin", "");
        storeUser(admin);
    }

    public static Collection<User> getAllUsers() {
    return userDatabase.values();
}

    public static void saveTaxCalculations(RegularUser regularUser, TaxCalculation taxCalculation) {
        regularUser.saveTaxCalculation(taxCalculation);
    }

    public static void storeUser(User user) {
        userDatabase.put(user.getUsername(), user);
        System.out.println("User stored in the database: " + user.getUsername());
    }

    public static User getUser(String username) {
        return userDatabase.get(username);
    }

    public static void removeUser(String username) {
        userDatabase.remove(username);
        System.out.println("User removed from the database: " + username);
    }

    public static void displayAllUsers() {
    Collection<User> allUsers = getAllUsers();

    if (allUsers.isEmpty()) {
        System.out.println("No users in the database.");
    } else {
        System.out.println("Users in the database:");
        for (User user : allUsers) {
            System.out.println(user);
        }
    }
}

    public static void setupDatabase() {
        DatabaseSetup.setupDatabase();
    }

    // Adicione o método para autenticar usuários
    public static User authenticateUser(String username, String password) {
        User user = getUser(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
