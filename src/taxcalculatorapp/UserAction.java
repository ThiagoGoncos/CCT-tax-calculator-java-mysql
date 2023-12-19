/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

/**
 *
 * @author thiagogoncos
 */
enum UserAction {
    MODIFY_PROFILE("Modify Profile"),
    ACCESS_USERS_LIST("Access Users List"),
    REMOVE_USER("Remove User"),
    REVIEW_OPERATIONS("Review Operations"),
    EXIT("Exit");

    private final String description;

    UserAction(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
