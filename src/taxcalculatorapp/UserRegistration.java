import java.util.Scanner;

/**
 *
 * @author kelvindumas
 */

public class UserRegistration {

    public static RegularUser registerUser() {
        Scanner scanner = new Scanner(System.in);

        String username;
        do {
            System.out.print("Enter new username (at least 8 characters): ");
            username = scanner.next();

            if (username.length() < 8 || Database.getUser(username) != null) {
                System.out.println("Invalid username. Please try again.");
            }
        } while (username.length() < 8 || Database.getUser(username) != null);

        String password;
        do {
            System.out.print("Enter new password (at least 6 letters and 2 numbers): ");
            password = scanner.next();

            if (!isValidPassword(password)) {
                System.out.println("Invalid password. Please try again.");
            }
        } while (!isValidPassword(password));

        String name;
        do {
            System.out.print("Enter your name (only letters): ");
            name = scanner.next();

            if (!isValidName(name)) {
                System.out.println("Invalid name. Please try again.");
            }
        } while (!isValidName(name));

        String surname;
        do {
            System.out.print("Enter your surname (only letters): ");
            surname = scanner.next();

            if (!isValidName(surname)) {
                System.out.println("Invalid surname. Please try again.");
            }
        } while (!isValidName(surname));

        System.out.print("Enter job role: ");
        String jobRole = scanner.next();

        RegularUser newUser = new RegularUser(username, password, name, surname, jobRole) {};

        DatabaseWriter databaseWriter = new DatabaseWriter();

        if (databaseWriter.addUser(newUser)) {
            System.out.println("User registration successful. Welcome, " + username + "!");
            System.out.println();
            return newUser;
        } else {
            System.out.println("User registration failed. Please try again.");
            System.out.println();
            return null;
        }
    }

    private static boolean isValidPassword(String password) {
        int letterCount = 0;
        int digitCount = 0;
        for (var ch : password.toCharArray()) {
            if (Character.isLetter(ch)) {
                letterCount++;
            } else if (Character.isDigit(ch)) {
                digitCount++;
            }
        }
        return letterCount >= 6 && digitCount >= 2;
    }

    private static boolean isValidName(String name) {
        return name.matches("^[a-zA-Z]+$");
    }
}
