import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Admin extends User {

    private String adminTitle;

    public Admin(String username, String password, String name, String surname) {
        super(username, password, name, surname, UserType.ADMIN);
    }

    public String getAdminTitle() {
        return adminTitle;
    }

    public void setAdminTitle(String adminTitle) {
        this.adminTitle = adminTitle;
    }

    @Override
    public void handleAdminActions() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            try {
                System.out.println();
                System.out.println("Admin actions:");
                for (UserAction action : UserAction.values()) {
                    System.out.println(action.ordinal() + 1 + ". " + action.getDescription());
                }

                System.out.println();

                System.out.println("Admin Information:");
                System.out.println(this.toString());

                System.out.println();

                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                if (choice > 0 && choice <= UserAction.values().length) {
                    UserAction selectedAction = UserAction.values()[choice - 1];

                    switch (selectedAction) {
                        case MODIFY_PROFILE -> {
                            System.out.print("Enter new username: ");
                            String newUsername = scanner.next();
                            System.out.print("Enter new password: ");
                            String newPassword = scanner.next();
                            System.out.print("Enter new name: ");
                            String newName = scanner.next();
                            System.out.print("Enter new surname: ");
                            String newSurname = scanner.next();

                            this.modifyProfile(newName, newSurname);
                            this.setUsername(newUsername);
                            this.setPassword(newPassword);

                            Database.storeUser(this);
                        }
                        case ACCESS_USERS_LIST -> displayAllUsers();
                        case REMOVE_USER -> {
                            System.out.print("Enter username to remove: ");
                            String usernameToRemove = scanner.next();
                            Database.removeUser(usernameToRemove);
                        }
                        case REVIEW_OPERATIONS -> {
    System.out.println("Tax calculations for all users:");

    for (User user : Database.getAllUsers()) {
        if (user instanceof RegularUser regularUser) {
            System.out.println("User: " + regularUser.getUsername());

            List<TaxCalculation> taxCalculations = regularUser.getTaxCalculations();

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
                        case EXIT -> {
                            System.out.println("Exiting admin actions.");
                            System.out.println();
                        }
                        default -> System.out.println("Invalid choice. Please try again.");
                    }
                    System.out.println();

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

    @Override
    public String getJobRole() {
        return "Administrator";
    }

    @Override
    public void modifyProfile(String name, String surname) {
    }

    public static void main(String[] args) {
        Admin admin = new Admin("CCT", "Dublin", "AdminName", "AdminSurname");
        admin.setAdminTitle("AdminTitle");

        DatabaseWriter writer = new DatabaseWriter();
        writer.addUser(admin);

        admin.handleAdminActions();
    }
    
    private void displayAllUsers() {
    Collection<User> allUsers = Database.getAllUsers();

    if (allUsers.isEmpty()) {
        System.out.println("No users in the database.");
    } else {
        System.out.println("Users in the database:");
        for (User user : allUsers) {
            System.out.println(user);
        }
    }
    }
    
    String taxCalculationToString(TaxCalculation taxCalculation) {
        return "Gross Income: " + taxCalculation.getGrossIncome()
                + ", Tax Credits: " + taxCalculation.getTaxCredits()
                + ", Income Tax: " + taxCalculation.getIncomeTax()
                + ", USC: " + taxCalculation.getUsc()
                + ", PRSI: " + taxCalculation.getPrsi();
    }
}
