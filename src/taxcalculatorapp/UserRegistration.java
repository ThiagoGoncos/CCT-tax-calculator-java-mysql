/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

/**
 *
 * @author kelvindumas
 */
class UserRegistration {
    public static RegularUser registerUser() {
        Scanner scanner = new Scanner(System.in);

        // Obter nome de usuário
        String username;
        do {
            System.out.print("Enter new username (at least 8 characters): ");
            username = scanner.next();

            if (username.length() < 8) {
                System.out.println("Username must have at least 8 characters. Please try again.");
            }
        } while (username.length() < 8 || Database.getUser(username) != null);

        // Obter senha
        String password;
        do {
            System.out.print("Enter new password (at least 6 letters and 2 numbers): ");
            password = scanner.next();

            // Verificar complexidade da senha
            if (!isValidPassword(password)) {
                System.out.println("Password must have at least 6 letters and 2 numbers. Please try again.");
            }
        } while (!isValidPassword(password));

        // Obter nome
        String name;
        do {
            System.out.print("Enter your name (only letters): ");
            name = scanner.next();

            if (!isValidName(name)) {
                System.out.println("Name must contain only letters. Please try again.");
            }
        } while (!isValidName(name));

        // Obter sobrenome
        String surname;
        do {
            System.out.print("Enter your surname (only letters): ");
            surname = scanner.next();

            if (!isValidName(surname)) {
                System.out.println("Surname must contain only letters. Please try again.");
            }
        } while (!isValidName(surname));

        // Obter job role
        System.out.print("Enter job role: ");
        String jobRole = scanner.next();

        RegularUser newUser = new RegularUser(username, password, name, surname, jobRole);

        Database.storeUser(newUser);

        System.out.println("User registration successful. Welcome, " + newUser.getUsername() + "!");
        return newUser;
    }

    // Métodos auxiliares para validar senha e nome
    private static boolean isValidPassword(String password) {
        // Verifica se a senha tem pelo menos 6 letras e 2 números
        int letterCount = 0;
        int digitCount = 0;
        for (char ch : password.toCharArray()) {
            if (Character.isLetter(ch)) {
                letterCount++;
            } else if (Character.isDigit(ch)) {
                digitCount++;
            }
        }
        return letterCount >= 6 && digitCount >= 2;
    }

    private static boolean isValidName(String name) {
        // Verifica se o nome contém apenas letras
        return name.matches("^[a-zA-Z]+$");
    }
}
