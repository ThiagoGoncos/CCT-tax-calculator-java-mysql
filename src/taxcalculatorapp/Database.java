/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/*
@author ThiagoGoncos (2022161) and KelvinDumas (2022264)
GitHub Link: https://github.com/Thiago2022161/taxcalculatorapp
Presentation Video Link: https://www.youtube.com/watch?v=Fmmwt0PodRk
*/

// Definition of an abstract Database class serving as a centralized storage for user-related operations
public abstract class Database {

    // Static map to store user data with usernames as keys
    private static final Map<String, User> userDatabase = new HashMap<>();

    // Static block to initialize the database with an Admin user
    static {
        Admin admin = new Admin("CCT", "Dublin", "Admin", "");
        storeUser(admin);
    }

    // Method to save tax calculations for a RegularUser
    public static void saveTaxCalculations(RegularUser regularUser, TaxCalculation taxCalculation) {
        regularUser.saveTaxCalculation(taxCalculation);
    }

    // Method to store a User in the database
    public static void storeUser(User user) {
        userDatabase.put(user.getUsername(), user);
        System.out.println("User stored in the database: " + user.getUsername());
    }

    // Method to retrieve a User based on the provided username
    public static User getUser(String username) {
        return userDatabase.get(username);
    }

    // Method to remove a User from the database based on the provided username
    public static void removeUser(String username) {
        userDatabase.remove(username);
        System.out.println("User removed from the database: " + username);
    }

    // Method to retrieve a collection of all Users in the database
    public static Collection<User> getAllUsers() {
        return userDatabase.values();
    }

    // Method to set up the database, typically called at the start of the application
    public static void setupDatabase() {
        DatabaseSetup.setupDatabase();
    }

    // Method to authenticate a user based on provided username and password
    public static User authenticateUser(String username, String password) {
        User user = getUser(username);
        // Check if the user exists and the provided password is correct
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        // Return null if authentication fails
        return null;
    }
}
