/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;
import java.util.Scanner;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.SQLException;


/**
 *
 * @author thiagogoncos
 */
import java.util.Scanner;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.SQLException;

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
    public void modifyProfile(String name, String surname) {
        super.setName(name);
        super.setSurname(surname);
        System.out.println("Admin profile modified: " + this);
    }

    public void grantAdminPrivileges(RegularUser user) {
        System.out.println("Admin privileges granted to user: " + user.getUsername());
    }

    private void accessUsersList() {
        Database.displayAllUsers();
    }

    private void removeUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username to remove: ");
        String usernameToRemove = scanner.next();
        Database.removeUser(usernameToRemove);
    }

    private void reviewOperations() {
        DatabaseReader databaseReader = new DatabaseReader();
        databaseReader.reviewOperations(this);
    }

    private void handleAdminActions() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
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
                    case MODIFY_PROFILE:
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
                        break;
                    case ACCESS_USERS_LIST:
                        accessUsersList();
                        break;
                    case REMOVE_USER:
                        removeUser();
                        break;
                    case REVIEW_OPERATIONS:
                        reviewOperations();
                        break;
                    case EXIT:
                        System.out.println("Exiting admin actions.");
                        System.out.println();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                System.out.println();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != UserAction.EXIT.ordinal() + 1);
    }

    public static void main(String[] args) {
        Admin admin = new Admin("CCT", "Dublin", "AdminName", "AdminSurname");
        admin.setAdminTitle("AdminTitle");

        DatabaseWriter writer = new DatabaseWriter();
        writer.addUser(admin);

        admin.handleAdminActions();
    }

    @Override
    public String getJobRole() {
        return "Administrator";
    }
}
