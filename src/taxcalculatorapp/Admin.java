/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

/**
 *
 * @author kelvidumas
 */
class Admin extends User {
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

        	System.out.print("Enter gross income for user " + regularUser.getUsername() + ": ");
        	double grossIncome = scanner.nextDouble();
        	System.out.print("Enter tax credits for user " + regularUser.getUsername() + ": ");
        	double taxCredits = scanner.nextDouble();

        	regularUser.calculateAndSaveTaxes(grossIncome, taxCredits);

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
}
