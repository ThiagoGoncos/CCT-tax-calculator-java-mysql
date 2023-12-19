/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

/**
 *
 * @author thiagogoncos
 */
// Abstract class representing a User in a tax calculation system
public abstract class User {

    // User attributes
    private String username;
    private String password;
    private String name;
    private String surname;
    private UserType userType;

    // Constructor to initialize User attributes
    public User(String username, String password, String name, String surname, UserType userType) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.userType = userType;
    }

    // Getter and setter methods for the username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and setter methods for the password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and setter methods for the name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter methods for the surname
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    // Getter and setter methods for the UserType (Admin or RegularUser)
    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    // Abstract method to be implemented by subclasses for modifying the user profile
    public abstract void modifyProfile(String name, String surname);

    // Abstract method to be implemented by subclasses for retrieving the job role
    public abstract String getJobRole();

    // Override toString method to provide a string representation of the User
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}