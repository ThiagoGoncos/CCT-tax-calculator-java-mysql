/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

/**
 *
 * @author thiagogoncos
 */
public class UserLogin {
    public static User loginUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.next();

        System.out.print("Enter password: ");
        String password = scanner.next();

        // Verifica se o usuário admin está tentando fazer login
        if (username.equals("CCT") && password.equals("Dublin")) {
            return Database.getUser(username);
        }

        User authenticatedUser = Database.getUser(username);

        if (authenticatedUser != null && authenticatedUser.getPassword().equals(password)) {
            System.out.println("User login successful: " + authenticatedUser.getUsername());
            return authenticatedUser;
        } else {
            System.out.println("Authentication failed. Please check your username and password.");
            return null;
        }
    }
}
