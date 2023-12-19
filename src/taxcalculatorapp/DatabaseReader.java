/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

import java.util.List;

// Definition of a DatabaseReader class, extending the abstract Database class
public class DatabaseReader extends Database {

    // Method to retrieve a User based on username and password
    public User getUser(String username, String password) {
        // Call the parent class method to get the user by username
        User user = Database.getUser(username);

        // Check if the user exists and the provided password is correct
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            // Return null if the user doesn't exist or the password is incorrect
            return null;
        }
    }

    // Method to retrieve tax calculations for a RegularUser based on username
    public List<TaxCalculation> getTaxCalculations(String username) {
        // Call the local method to get a User by username
        User user = getUser(username);

        // Check if the user exists and is an instance of RegularUser
        if (user != null && user instanceof RegularUser) {
            // Return the tax calculations associated with the RegularUser
            return ((RegularUser) user).getTaxCalculations();
        }

        // Return null if the user is not found or is not a RegularUser
        return null;
    }
}