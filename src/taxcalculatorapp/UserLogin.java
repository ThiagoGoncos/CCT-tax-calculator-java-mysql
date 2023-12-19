/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

/**
 *
 * @author kelvindumas
 */
public class UserLogin {
    public static User loginUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.next();

        System.out.print("Enter password: ");
        String password = scanner.next();

        DatabaseReader reader = new DatabaseReader();
        User authenticatedUser = reader.getUser(username, password);

        if (authenticatedUser != null) {
            System.out.println("User login successful: " + authenticatedUser.getUsername());
            return authenticatedUser;
        } else {
            System.out.println("Authentication failed. Please check your username and password.");
            return null;
        }
    }
}
