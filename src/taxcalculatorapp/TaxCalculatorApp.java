/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaxCalculatorApp {

    public static void main(String[] args) {
        DatabaseSetup.setupDatabase();
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
                System.out.println();
                System.out.print("Enter your choice: ");
                initialChoice = scanner.nextInt();

                if (initialChoice < 1 || initialChoice > 3) {
                    System.out.println("Invalid input. Please enter a number between 1 and 3.");
                    continue;
                }

                switch (initialChoice) {
                case 1 -> {
                    User authenticatedUser = UserLogin.loginUser();
                    if (authenticatedUser != null) {
                        UserType userType = authenticatedUser.getUserType();
                        if (userType == UserType.ADMIN) {
                            Admin admin = (Admin) authenticatedUser;
                            admin.handleAdminActions();
                        } else {
                            RegularUser regularUser = (RegularUser) authenticatedUser;
                            handleRegularUserActions(regularUser);
                        }
                    } else {
                        System.out.println("Authentication failed. Exiting application.");
                        System.out.println();
                    }
                }
                case 2 -> {
                    RegularUser newUser = UserRegistration.registerUser();
                    if (newUser != null) {
                        handleRegularUserActions(newUser);
                    }
                }
                case 3 -> {
                    System.out.println("Exiting application.");
                    System.out.println();
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please, enter a valid number.");
            System.out.println();
            scanner.next();
        }
    } while (initialChoice != 3);
}

    private static String taxCalculationToString(TaxCalculation taxCalculation) {
        return "Gross Income: " + taxCalculation.getGrossIncome()
                + ", Tax Credits: " + taxCalculation.getTaxCredits()
                + ", Income Tax: " + taxCalculation.getIncomeTax()
                + ", USC: " + taxCalculation.getUsc()
                + ", PRSI: " + taxCalculation.getPrsi();
    }

    private static void handleRegularUserActions(RegularUser regularUser) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println();
            System.out.println("Regular user actions:");
            System.out.println("1. Modify profile");
            System.out.println("2. Calculate and Save taxes");
            System.out.println("3. Exit");

            System.out.println();
            System.out.println("User Information:");
            System.out.println(regularUser.toString());

            System.out.println();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> modifyUserProfile(regularUser);
                case 2 -> {
                    System.out.print("Enter gross income: ");
                    double grossIncome = scanner.nextDouble();
                    System.out.print("Enter tax credits: ");
                    double taxCredits = scanner.nextDouble();

                    calculateAndSaveTaxes(regularUser, grossIncome, taxCredits);
                }
                case 3 -> {
                    System.out.println("Exiting regular user actions.");
                    System.out.println();
                }
                default -> {
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println();
                }
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
    }

    private static void calculateAndSaveTaxes(RegularUser regularUser, double grossIncome, double taxCredits) {
        TaxCalculation taxCalculation = regularUser.calculateTaxes(grossIncome, taxCredits);

        System.out.println("Tax calculation for user " + regularUser.getUsername() + ":");
        System.out.println(taxCalculationToString(taxCalculation));

        DatabaseWriter.saveUserDataAndTaxes(regularUser, grossIncome, taxCredits,
                taxCalculation.getIncomeTax(), taxCalculation.getUsc(), taxCalculation.getPrsi());
    }
}
