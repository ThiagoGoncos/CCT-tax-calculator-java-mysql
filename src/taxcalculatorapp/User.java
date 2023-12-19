/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

/**
 *
 * @author kelvindumas
 */

public abstract class User {

    private String username;
    private String password;
    private String name;
    private String surname;
    private UserType userType;

    public User(String username, String password, String name, String surname, UserType userType) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public abstract void handleAdminActions();
    
    public abstract void modifyProfile(String name, String surname);

    public abstract String getJobRole();

    @Override
    public String toString() {
        return "User{"
                + "username='" + username + '\''
                + ", password='" + password + '\''
                + ", name='" + name + '\''
                + ", surname='" + surname + '\''
                + '}';
    }
}