/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

/**
 *
 * @author thiagogoncos
 */

import java.util.Scanner;

// Class responsible for handling user login functionality
public class UserLogin {

    // Method to perform user login
    public static User loginUser() {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for username
        System.out.print("Enter username: ");
        String username = scanner.next();

        // Prompt user for password
        System.out.print("Enter password: ");
        String password = scanner.next();

        // Create a DatabaseReader instance to interact with the user database
        DatabaseReader reader = new DatabaseReader();

        // Attempt to retrieve a user from the database based on entered username and password
        User authenticatedUser = reader.getUser(username, password);

        // Check if user authentication was successful
        if (authenticatedUser != null) {
            // Display a success message if authentication is successful
            System.out.println("User login successful: " + authenticatedUser.getUsername());
            return authenticatedUser;  // Return the authenticated user
        } else {
            // Display an error message if authentication fails
            System.out.println("Authentication failed. Please check your username and password.");
            return null;  // Return null to indicate authentication failure
        }
    }
}