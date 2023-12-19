/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

/**
 *
 * @author thiagogoncos
 */
class UserRegistration {
    public static RegularUser registerUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter new username: ");
        String username = scanner.next();

        if (Database.getUser(username) != null) {
            System.out.println("Username already exists. Please choose a different username.");
            return null;
        }

        System.out.print("Enter new password: ");
        String password = scanner.next();

        System.out.print("Enter your name: ");
        String name = scanner.next();

        System.out.print("Enter your surname: ");
        String surname = scanner.next();

        System.out.print("Enter job role: ");
        String jobRole = scanner.next();

        RegularUser newUser = new RegularUser(username, password, name, surname, jobRole);

        Database.storeUser(newUser);

        System.out.println("User registration successful. Welcome, " + newUser.getUsername() + "!");
        return newUser;
    }
}
