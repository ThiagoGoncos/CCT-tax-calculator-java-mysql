/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

/**
 *
 * @author kelvindumas
 */
class Database {
    private static Map<String, User> userDatabase = new HashMap<>();

    static {
        Admin admin = new Admin("CCT", "Dublin", "Admin", "");
        storeUser(admin);
    }

    public static Collection<User> getAllUsers() {
        return userDatabase.values();
    }

    public static void saveTaxCalculations(RegularUser regularUser, TaxCalculation taxCalculation) {
        regularUser.saveTaxCalculation(taxCalculation);
    }

    public static void storeUser(User user) {
        userDatabase.put(user.getUsername(), user);
        System.out.println("User stored in the database: " + user.getUsername());
    }

    public static User getUser(String username) {
        return userDatabase.get(username);
    }

    public static void removeUser(String username) {
        userDatabase.remove(username);
        System.out.println("User removed from the database: " + username);
    }

    public static void displayAllUsers() {
        System.out.println("Users in the database:");
        for (User user : userDatabase.values()) {
            System.out.println(user);
        }
    }
}
