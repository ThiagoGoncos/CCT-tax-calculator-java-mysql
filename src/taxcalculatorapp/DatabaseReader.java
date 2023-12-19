/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

/**
 *
 * @author thiagogoncos
 */
import java.util.List;
import static taxcalculatorapp.Database.getUser;


public class DatabaseReader extends Database {

    public User getUser(String username, String password) {
        User user = Database.getUser(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }
    
    public List<TaxCalculation> getTaxCalculations(String username) {
    User user = getUser(username);
    if (user != null && user instanceof RegularUser) {
        return ((RegularUser) user).getTaxCalculations();
    }
    return null;
}
}
