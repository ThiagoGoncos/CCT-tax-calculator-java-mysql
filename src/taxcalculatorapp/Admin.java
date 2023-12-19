/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;
import java.util.Scanner;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.SQLException;


/**
 *
 * @author kelvidumas
 */
public class Admin extends User {

	private String adminTitle;

	public Admin(String username, String password, String name, String surname) {
    	super(username, password, name, surname, UserType.ADMIN);
	}

	public String getAdminTitle() {
    	return adminTitle;
	}

	public void setAdminTitle(String adminTitle) {
    	this.adminTitle = adminTitle;
	}

	@Override
	public void modifyProfile(String name, String surname) {
    	super.setName(name);
    	super.setSurname(surname);
    	System.out.println("Admin profile modified: " + this);
	}

	public void grantAdminPrivileges(RegularUser user) {
    	System.out.println("Admin privileges granted to user: " + user.getUsername());
	}

	private static void reviewOperations(Admin admin) {
	Scanner scanner = new Scanner(System.in);

	for (User user : Database.getAllUsers()) {
    	if (user instanceof RegularUser) {
        	RegularUser regularUser = (RegularUser) user;
        	System.out.println("Tax calculations for user " + regularUser.getUsername() + ":");

        	for (TaxCalculation taxCalculation : regularUser.getTaxCalculations()) {
            	System.out.println(taxCalculationToString(taxCalculation));
        	}
    	}
	}
	}

	private static String taxCalculationToString(TaxCalculation taxCalculation) {
    	return "Gross Income: " + taxCalculation.getGrossIncome() +
            	", Tax Credits: " + taxCalculation.getTaxCredits() +
            	", Income Tax: " + taxCalculation.getIncomeTax() +
            	", USC: " + taxCalculation.getUsc() +
            	", PRSI: " + taxCalculation.getPrsi();
	}

	public void saveUserToDatabase(User user) {
    	DatabaseWriter writer = new DatabaseWriter();
    	writer.addUser(user);
	}

	public static void main(String[] args) {
    	// Adicione a seguinte linha para criar o usuário Admin
    	Admin admin = new Admin("CCT", "Dublin", "AdminName", "AdminSurname");
    	admin.setAdminTitle("AdminTitle"); // Defina o título do Admin, se necessário

    	// Chame o método para salvar o Admin no banco de dados
    	DatabaseWriter writer = new DatabaseWriter();
    	writer.addUser(admin);
	}

	@Override
	public String getJobRole() {
    	return "Administrator";
	}
}
