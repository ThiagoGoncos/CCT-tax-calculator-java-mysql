/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

import java.util.Scanner;

/*
@author ThiagoGoncos (2022161) and KelvinDumas (2022264)
GitHub Link: https://github.com/Thiago2022161/taxcalculatorapp
Presentation Video Link: https://www.youtube.com/watch?v=Fmmwt0PodRk
*/

// Class responsible for handling user registration functionality
public class UserRegistration {

    // Method to register a new RegularUser
    public static RegularUser registerUser() {
        Scanner scanner = new Scanner(System.in);

        String username;
        // Loop to prompt the user for a new username until a valid one is provided
        do {
            System.out.print("Enter new username (at least 8 characters): ");
            username = scanner.next();

            // Check if the username meets length requirements and is not already in use
            if (username.length() < 8 || Database.getUser(username) != null) {
                System.out.println("Invalid username. Please try again.");
            }
        } while (username.length() < 8 || Database.getUser(username) != null);

        String password;
        // Loop to prompt the user for a new password until a valid one is provided
        do {
            System.out.print("Enter new password (at least 6 letters and 2 numbers): ");
            password = scanner.next();

            // Check if the password meets the specified criteria
            if (!isValidPassword(password)) {
                System.out.println("Invalid password. Please try again.");
            }
        } while (!isValidPassword(password));

        String name;
        // Loop to prompt the user for a name until a valid one is provided
        do {
            System.out.print("Enter your name (only letters): ");
            name = scanner.next();

            // Check if the name contains only letters
            if (!isValidName(name)) {
                System.out.println("Invalid name. Please try again.");
            }
        } while (!isValidName(name));

        String surname;
        // Loop to prompt the user for a surname until a valid one is provided
        do {
            System.out.print("Enter your surname (only letters): ");
            surname = scanner.next();

            // Check if the surname contains only letters
            if (!isValidName(surname)) {
                System.out.println("Invalid surname. Please try again.");
            }
        } while (!isValidName(surname));

        // Prompt the user for job role
        System.out.print("Enter job role: ");
        String jobRole = scanner.next();

        // Create a new RegularUser instance with the provided information
        RegularUser newUser = new RegularUser(username, password, name, surname, jobRole) {
        };

        // Create a DatabaseWriter instance to interact with the user database
        DatabaseWriter databaseWriter = new DatabaseWriter();

        // Attempt to add the new user to the database
        if (databaseWriter.addUser(newUser)) {
            // Display a success message if user registration is successful
            System.out.println("User registration successful. Welcome, " + username + "!");
            System.out.println();
            return newUser;  // Return the newly registered user
        } else {
            // Display an error message if user registration fails
            System.out.println("User registration failed. Please try again.");
            System.out.println();
            return null;  // Return null to indicate registration failure
        }
    }

    // Method to check if a password meets the specified criteria
    private static boolean isValidPassword(String password) {
        int letterCount = 0;
        int digitCount = 0;
        // Loop through each character in the password to count letters and digits
        for (var ch : password.toCharArray()) {
            if (Character.isLetter(ch)) {
                letterCount++;
            } else if (Character.isDigit(ch)) {
                digitCount++;
            }
        }
        // Return true if the password meets the criteria, otherwise false
        return letterCount >= 6 && digitCount >= 2;
    }

    // Method to check if a name contains only letters
    private static boolean isValidName(String name) {
        return name.matches("^[a-zA-Z]+$");
    }
}
