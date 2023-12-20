/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

/*
@author ThiagoGoncos (2022161) and KelvinDumas (2022264)
GitHub Link: https://github.com/Thiago2022161/taxcalculatorapp
Presentation Video Link: https://www.youtube.com/watch?v=Fmmwt0PodRk
*/

// Enum representing various actions that can be performed by an Admin
public enum UserAction {

    // Enum constants representing different actions
    MODIFY_PROFILE("Modify Profile"),
    ACCESS_USERS_LIST("Access Users List"),
    REMOVE_USER("Remove User"),
    REVIEW_OPERATIONS("Review Operations"),
    EXIT("Exit");

    // Private field to store the description of each action
    private final String description;

    // Constructor to initialize the description for each action
    UserAction(String description) {
        this.description = description;
    }

    // Getter method to retrieve the description of an action
    public String getDescription() {
        return description;
    }
}
