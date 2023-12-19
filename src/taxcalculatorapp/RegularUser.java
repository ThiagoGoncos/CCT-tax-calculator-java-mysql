/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package taxcalculatorapp;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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
        System.out.println("Regular user profile modified: " + this);
    }

    public void calculateAndSaveTaxes(double grossIncome, double taxCredits) {
    TaxCalculator taxCalculator = new TaxCalculatorImpl();

    // Verificar se as taxas são números
    if (!isValidNumber(grossIncome) || !isValidNumber(taxCredits)) {
        System.out.println("Invalid input. Please enter valid numbers.");
        return;
    }

    double incomeTax = taxCalculator.calculateIncomeTax(grossIncome, taxCredits);
    double usc = taxCalculator.calculateUSC(grossIncome);
    double prsi = taxCalculator.calculatePRSI(grossIncome);

    System.out.println("Income Tax (PAYE): " + incomeTax);
    System.out.println("USC: " + usc);
    System.out.println("PRSI: " + prsi);

    saveTaxCalculations(grossIncome, taxCredits, incomeTax, usc, prsi);

    System.out.println("Tax calculations saved successfully!");
}

    private static boolean isValidNumber(double number) {
        return !Double.isNaN(number) && !Double.isInfinite(number);
    }

    private void saveTaxCalculations(double grossIncome, double taxCredits, double incomeTax, double usc, double prsi) {
        TaxCalculation taxCalculation = new TaxCalculation(grossIncome, taxCredits, incomeTax, usc, prsi);
        Database.saveTaxCalculations(this, taxCalculation);
    }

    public List<TaxCalculation> getTaxCalculations() {
        return taxCalculations;
    }

    public void saveTaxCalculation(TaxCalculation taxCalculation) {
        taxCalculations.add(taxCalculation);
    }
}
