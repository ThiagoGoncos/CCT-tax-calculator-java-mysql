/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package taxcalculatorapp;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.sql.SQLException;

public class RegularUser extends User {

    private String jobRole;
    private List<TaxCalculation> taxCalculations = new ArrayList<>();

    public RegularUser(String username, String password, String name, String surname, String jobRole) {
        super(username, password, name, surname, UserType.REGULAR_USER);
        this.jobRole = jobRole;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    @Override
    public void modifyProfile(String name, String surname) {
        super.setName(name);
        super.setSurname(surname);
        System.out.println("RegularUser profile modified: " + this);
    }

    public TaxCalculation calculateTaxes(double grossIncome, double taxCredits) {
        TaxCalculator taxCalculator = new TaxCalculatorImpl();

        double incomeTax = taxCalculator.calculateIncomeTax(grossIncome, taxCredits);
        double usc = taxCalculator.calculateUSC(grossIncome);
        double prsi = taxCalculator.calculatePRSI(grossIncome);

        TaxCalculation taxCalculation = new TaxCalculation(grossIncome, taxCredits, incomeTax, usc, prsi);
        return taxCalculation;
    }

    public void saveTaxCalculations(double grossIncome, double taxCredits) {
        TaxCalculator taxCalculator = new TaxCalculatorImpl();

        double incomeTax = taxCalculator.calculateIncomeTax(grossIncome, taxCredits);
        double usc = taxCalculator.calculateUSC(grossIncome);
        double prsi = taxCalculator.calculatePRSI(grossIncome);

        TaxCalculation taxCalculation = new TaxCalculation(grossIncome, taxCredits, incomeTax, usc, prsi);

        taxCalculations.add(taxCalculation);

        DatabaseWriter.saveUserDataAndTaxes(this, grossIncome, taxCredits, incomeTax, usc, prsi);
        System.out.println("Tax calculations saved successfully!");
    }

    public List<TaxCalculation> getTaxCalculations() {
        return taxCalculations;
    }

    public void saveTaxCalculation(TaxCalculation taxCalculation) {
        taxCalculations.add(taxCalculation);
    }

    public void saveUserToDatabase() {
        DatabaseWriter writer = new DatabaseWriter();
        writer.addUser(this);
    }
}
