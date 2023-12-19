/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// Definition of the Admin class that extends the User class
public class Admin extends User {

    // Additional field specific to Admin
    private String adminTitle;

    // Constructor for creating an Admin instance
    public Admin(String username, String password, String name, String surname) {
        super(username, password, name, surname, UserType.ADMIN);
    }

    // Getter method for the adminTitle field
    public String getAdminTitle() {
        return adminTitle;
    }

    // Setter method for the adminTitle field
    public void setAdminTitle(String adminTitle) {
        this.adminTitle = adminTitle;
    }

    // Method to handle various admin actions using a menu-driven approach
    public void handleAdminActions() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        // Menu loop for admin actions
        do {
            try {
                // Display available actions for the admin
                System.out.println("Admin actions:");
                for (UserAction action : UserAction.values()) {
                    System.out.println(action.ordinal() + 1 + ". " + action.getDescription());
                }

                // Display admin information
                System.out.println("Admin Information:");
                System.out.println(this.toString());

                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                // Perform the selected action based on user input
                if (choice > 0 && choice <= UserAction.values().length) {
                    UserAction selectedAction = UserAction.values()[choice - 1];

                    switch (selectedAction) {
                        // Case: MODIFY_PROFILE - Modify admin profile
                        case MODIFY_PROFILE -> {
                            // Input new profile information
                            System.out.print("Enter new username: ");
                            String newUsername = scanner.next();
                            System.out.print("Enter new password: ");
                            String newPassword = scanner.next();
                            System.out.print("Enter new name: ");
                            String newName = scanner.next();
                            System.out.print("Enter new surname: ");
                            String newSurname = scanner.next();

                            // Modify admin profile and update in the database
                            this.modifyProfile(newName, newSurname);
                            this.setUsername(newUsername);
                            this.setPassword(newPassword);

                            Database.storeUser(this);
                        }
                        // Case: ACCESS_USERS_LIST - Display all users in the database
                        case ACCESS_USERS_LIST ->
                            displayAllUsers();
                        // Case: REMOVE_USER - Remove a user from the database
                        case REMOVE_USER -> {
                            System.out.print("Enter username to remove: ");
                            String usernameToRemove = scanner.next();
                            Database.removeUser(usernameToRemove);
                        }
                        // Case: REVIEW_OPERATIONS - Display tax calculations for all regular users
                        case REVIEW_OPERATIONS -> {
                            System.out.println("Tax calculations for all users:");

                            // Iterate over all users and display tax calculations for regular users
                            for (User user : Database.getAllUsers()) {
                                if (user instanceof RegularUser regularUser) {
                                    System.out.println("User: " + regularUser.getUsername());

                                    List<TaxCalculation> taxCalculations = regularUser.getTaxCalculations();

                                    // Display tax calculations if available, otherwise indicate no calculations
                                    if (taxCalculations != null && !taxCalculations.isEmpty()) {
                                        for (TaxCalculation taxCalculation : taxCalculations) {
                                            System.out.println(taxCalculationToString(taxCalculation));
                                        }
                                    } else {
                                        System.out.println("No tax calculations available for this user.");
                                    }

                                    System.out.println();
                                }
                            }
                        }
                        // Case: EXIT - Exit admin actions
                        case EXIT -> {
                            System.out.println("Exiting admin actions.");
                            System.out.println();
                        }
                        // Default case: Invalid choice
                        default ->
                            System.out.println("Invalid choice. Please try again.");
                    }

                    // Pause and wait for user input before continuing
                    System.out.println("Press Enter to continue...");
                    new Scanner(System.in).nextLine();
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please, enter a valid number.");
                System.out.println();
                scanner.next();
            }
        } while (choice != UserAction.EXIT.ordinal() + 1);
    }

    // Override method to return the job role of the Admin
    @Override
    public String getJobRole() {
        return "Administrator";
    }

    // Override method to modify admin profile (not used)
    @Override
    public void modifyProfile(String name, String surname) {
        // Method body intentionally left blank for Admin
    }

    // Main method to demonstrate Admin functionality
    public static void main(String[] args) {
        // Create an instance of Admin
        Admin admin = new Admin("CCT", "Dublin", "AdminName", "AdminSurname");
        admin.setAdminTitle("AdminTitle");

        // Use DatabaseWriter to add the Admin to the database
        DatabaseWriter writer = new DatabaseWriter();
        writer.addUser(admin);

        // Invoke admin actions
        admin.handleAdminActions();
    }

    // Method to display all users in the database
    private void displayAllUsers() {
        Collection<User> allUsers = Database.getAllUsers();

        // Display user information if users are present, otherwise indicate no users
        if (allUsers.isEmpty()) {
            System.out.println("No users in the database.");
        } else {
            System.out.println("Users in the database:");
            for (User user : allUsers) {
                System.out.println(user);
            }
        }
    }

    // Method to convert TaxCalculation object to a string representation
    String taxCalculationToString(TaxCalculation taxCalculation) {
        return "Gross Income: " + taxCalculation.getGrossIncome()
                + ", Tax Credits: " + taxCalculation.getTaxCredits()
                + ", Income Tax: " + taxCalculation.getIncomeTax()
                + ", USC: " + taxCalculation.getUsc()
                + ", PRSI: " + taxCalculation.getPrsi();
    }
}