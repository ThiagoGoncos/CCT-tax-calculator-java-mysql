/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package taxcalculatorapp;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author thiagogoncos
 */
public class TaxCalculatorApp {
    public static void main(String[] args) {
        showMainMenu();
    }

    private static void showMainMenu() {
    Scanner scanner = new Scanner(System.in);
    int initialChoice = -1;

    do {
        try {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            initialChoice = scanner.nextInt();

            if (initialChoice < 1 || initialChoice > 3) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                continue;
            }

            switch (initialChoice) {
            case 1:
                User authenticatedUser = UserLogin.loginUser();
                if (authenticatedUser != null) {
                    UserType userType = authenticatedUser.getUserType();
                    if (userType == UserType.ADMIN) {
                        Admin admin = (Admin) authenticatedUser;
                        handleAdminActions(admin);
                    } else {
                        RegularUser regularUser = (RegularUser) authenticatedUser;
                        handleRegularUserActions(regularUser);
                    }
                } else {
                    System.out.println("Authentication failed. Exiting application.");
                }
                break;
                case 2:
                    RegularUser newUser = UserRegistration.registerUser();
                    if (newUser != null) {
                        handleRegularUserActions(newUser);
                    }
                    break;
                case 3:
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
       } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please, enter a valid number.");
            scanner.next();
            continue; 
        }
    } while (initialChoice != 3);
}

    private static void handleAdminActions(Admin admin) {
    Scanner scanner = new Scanner(System.in);
    int choice;

    do {
        System.out.println("Admin actions:");
        for (UserAction action : UserAction.values()) {
            System.out.println(action.ordinal() + 1 + ". " + action.getDescription());
        }

        System.out.println("Admin Information:");
        System.out.println(admin.toString());

        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();

        if (choice > 0 && choice <= UserAction.values().length) {
            UserAction selectedAction = UserAction.values()[choice - 1];
            switch (selectedAction) {
                case MODIFY_PROFILE:
                    System.out.print("Enter new username: ");
                    String newUsername = scanner.next();
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.next();
                    System.out.print("Enter new name: ");
                    String newName = scanner.next();
                    System.out.print("Enter new surname: ");
                    String newSurname = scanner.next();

                    admin.modifyProfile(newName, newSurname);
                    admin.setUsername(newUsername);
                    admin.setPassword(newPassword);

                    Database.storeUser(admin);
                    break;
                case ACCESS_USERS_LIST:
                    Database.displayAllUsers();
                    break;
                case REMOVE_USER:
                    System.out.print("Enter username to remove: ");
                    String usernameToRemove = scanner.next();
                    Database.removeUser(usernameToRemove);
                    break;
                case REVIEW_OPERATIONS:
                    reviewOperations(admin);
                    break;
                case EXIT:
                    System.out.println("Exiting admin actions.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    } while (choice != UserAction.EXIT.ordinal() + 1);
}
    
private static void reviewOperations(Admin admin) {
        for (User user : Database.getAllUsers()) {
            if (user instanceof RegularUser) {
                RegularUser regularUser = (RegularUser) user;
                System.out.println("Tax calculations for user " + regularUser.getUsername() + ":");

                for (TaxCalculation taxCalculation : regularUser.getTaxCalculations()) {
                    System.out.println(taxCalculationToString(taxCalculation));
                }
            }
        }
    }

    private static String taxCalculationToString(TaxCalculation taxCalculation) {
        // Criar uma representação de string para os cálculos de impostos
        return "Gross Income: " + taxCalculation.getGrossIncome() +
               ", Tax Credits: " + taxCalculation.getTaxCredits() +
               ", Income Tax: " + taxCalculation.getIncomeTax() +
               ", USC: " + taxCalculation.getUsc() +
               ", PRSI: " + taxCalculation.getPrsi();
    }
 

    private static void handleRegularUserActions(RegularUser regularUser) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Regular user actions:");
            System.out.println("1. Modify profile");
            System.out.println("2. Calculate and Save taxes");
            System.out.println("3. Exit");

            System.out.println("User Information:");
            System.out.println(regularUser.toString());

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    modifyUserProfile(regularUser);
                    break;
                case 2:
                    calculateAndSaveTaxes(regularUser);
                    break;
                case 3:
                    System.out.println("Exiting regular user actions.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 3);
    }

    private static void modifyUserProfile(RegularUser regularUser) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter new username: ");
        String newUsername = scanner.next();
        System.out.print("Enter new password: ");
        String newPassword = scanner.next();
        System.out.print("Enter new name: ");
        String newName = scanner.next();
        System.out.print("Enter new surname: ");
        String newSurname = scanner.next();

        regularUser.modifyProfile(newName, newSurname);
        regularUser.setUsername(newUsername);
        regularUser.setPassword(newPassword);

        Database.storeUser(regularUser);
        System.out.println("User profile modified: " + regularUser.toString());
    }

    private static void calculateAndSaveTaxes(RegularUser regularUser) {
        regularUser.calculateAndSaveTaxes();
    }
}
