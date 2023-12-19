/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

/**
 *
 * @author thiagogoncos
 */

// Definition of a class representing a TaxCalculation
public class TaxCalculation {

    // Fields to store various tax-related values
    private final double grossIncome; // Gross income of the individual
    private final double taxCredits; // Tax credits associated with the individual
    private final double incomeTax; // Calculated income tax amount
    private final double usc; // Calculated Universal Social Charge (USC)
    private final double prsi; // Calculated Pay-Related Social Insurance (PRSI)

    // Constructor to initialize a TaxCalculation object with specified attributes
    public TaxCalculation(double grossIncome, double taxCredits, double incomeTax, double usc, double prsi) {
        this.grossIncome = grossIncome;
        this.taxCredits = taxCredits;
        this.incomeTax = incomeTax;
        this.usc = usc;
        this.prsi = prsi;
    }

    // Getter method to retrieve the gross income value
    public double getGrossIncome() {
        return grossIncome;
    }

    // Getter method to retrieve the tax credits value
    public double getTaxCredits() {
        return taxCredits;
    }

    // Getter method to retrieve the income tax value
    public double getIncomeTax() {
        return incomeTax;
    }

    // Getter method to retrieve the USC value
    public double getUsc() {
        return usc;
    }

    // Getter method to retrieve the PRSI value
    public double getPrsi() {
        return prsi;
    }
}