/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Definition of a class representing a regular user, extending the User class
public class RegularUser extends User {

    // Field to store the job role of the regular user
    private String jobRole;

    // List to store tax calculations associated with the regular user
    private final List<TaxCalculation> taxCalculations = new ArrayList<>();

    // Constructor to initialize a RegularUser object with specified attributes
    public RegularUser(String username, String password, String name, String surname, String jobRole) {
        super(username, password, name, surname, UserType.REGULAR_USER);
        this.jobRole = jobRole;
    }

    // Getter method to retrieve the job role of the regular user
    @Override
    public String getJobRole() {
        return jobRole;
    }

    // Setter method to update the job role of the regular user
    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    // Override of the modifyProfile method to print a message about profile modification
    @Override
    public void modifyProfile(String name, String surname) {
        System.out.println("RegularUser profile modified: " + this);
    }

    // Method to calculate and save tax calculations for the regular user
    public TaxCalculation calculateAndSaveTaxes(double grossIncome, double taxCredits) throws SQLException {
        // Create an instance of TaxCalculatorImpl for tax calculations
        TaxCalculator taxCalculator = new TaxCalculatorImpl();

        // Calculate income tax, USC, and PRSI using the TaxCalculator
        double incomeTax = taxCalculator.calculateIncomeTax(grossIncome, taxCredits);
        double usc = taxCalculator.calculateUSC(grossIncome);
        double prsi = taxCalculator.calculatePRSI(grossIncome);

        // Create a TaxCalculation object with the calculated values
        TaxCalculation taxCalculation = new TaxCalculation(grossIncome, taxCredits, incomeTax, usc, prsi);
        
        // Save the tax calculation by adding it to the taxCalculations list
        saveTaxCalculation(taxCalculation);
        
        // Return the calculated TaxCalculation object
        return taxCalculation;
    }

    // Method to retrieve an unmodifiable view of the tax calculations list
    List<TaxCalculation> getTaxCalculations() {
        return Collections.unmodifiableList(taxCalculations);
    }

    // Method to save a tax calculation by adding it to the taxCalculations list
    void saveTaxCalculation(TaxCalculation taxCalculation) {
        taxCalculations.add(taxCalculation);
    }
}