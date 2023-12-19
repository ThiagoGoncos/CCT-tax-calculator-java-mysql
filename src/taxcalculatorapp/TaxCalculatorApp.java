/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package taxcalculatorapp;

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
        int initialChoice;

        do {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            initialChoice = scanner.nextInt();

            switch (initialChoice) {
                case 1:
                    User authenticatedUser = UserLogin.loginUser();
                    if (authenticatedUser != null) {
                        if (authenticatedUser instanceof Admin) {
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
        } while (initialChoice != 3);
    }

    private static void handleAdminActions(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Admin actions:");
            System.out.println("1. Modify profile");
            System.out.println("2. Access list of users");
            System.out.println("3. Remove user");
            System.out.println("4. Review operations");
            System.out.println("5. Exit");

            // Mostra informações pessoais do admin
            System.out.println("Admin Information:");
            System.out.println(admin.toString());

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Lógica para modificar o perfil do admin
                    System.out.print("Enter new username: ");
                    String newUsername = scanner.next();
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.next();
                    System.out.print("Enter new name: ");
                    String newName = scanner.next();
                    System.out.print("Enter new surname: ");
                    String newSurname = scanner.next();

                    // Atualizar as informações do admin
                    admin.modifyProfile(newName, newSurname);
                    admin.setUsername(newUsername);
                    admin.setPassword(newPassword);

                    // Atualizar as informações na base de dados
                    Database.storeUser(admin);
                    break;
                case 2:
                    // Lógica para acessar lista de usuários
                    Database.displayAllUsers();
                    break;
                case 3:
                    // Lógica para remover usuário
                    System.out.print("Enter username to remove: ");
                    String usernameToRemove = scanner.next();
                    Database.removeUser(usernameToRemove);
                    break;
                case 4:
                    // Lógica para revisar operações e visualizar cálculos de impostos salvos
                    reviewOperations(admin);
                    break;
                case 5:
                    System.out.println("Exiting admin actions.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);
    }
private static void reviewOperations(Admin admin) {
        // Mostrar cálculos de impostos salvos por todos os usuários
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

        // Modificar o perfil do usuário
        regularUser.modifyProfile(newName, newSurname);
        regularUser.setUsername(newUsername);
        regularUser.setPassword(newPassword);

        // Atualizar as informações na base de dados
        Database.storeUser(regularUser);
        System.out.println("User profile modified: " + regularUser.toString());
    }

    private static void calculateAndSaveTaxes(RegularUser regularUser) {
        regularUser.calculateAndSaveTaxes();
    }
}
