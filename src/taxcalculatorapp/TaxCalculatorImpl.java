/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

// Classe TaxCalculatorImpl
// Implementation of the TaxCalculator interface
public class TaxCalculatorImpl implements TaxCalculator {

    // Method to calculate income tax based on gross income and tax credits
    @Override
    public double calculateIncomeTax(double grossIncome, double taxCredits) {
        // Calculate taxable income
        double taxableIncome = grossIncome - taxCredits;

        // Determine the payeRate based on gross income
        double payeRate;
        if (grossIncome <= 40000) {
            payeRate = 0.2;
        } else {
            payeRate = 0.4;
        }

        // Calculate and return income tax
        return taxableIncome * payeRate;
    }

    // Method to calculate USC (Universal Social Charge) based on gross income
    @Override
    public double calculateUSC(double grossIncome) {
        // Determine USC rate based on income thresholds
        double uscRate;
        if (grossIncome <= 12012) {
            uscRate = 0.0;
        } else if (grossIncome <= 22920) {
            uscRate = 0.005;
        } else if (grossIncome <= 70044) {
            uscRate = 0.02;
        } else {
            uscRate = 0.04;
        }

        // Calculate and return USC
        return grossIncome * uscRate;
    }

    // Method to calculate PRSI (Pay Related Social Insurance) based on gross income
    @Override
    public double calculatePRSI(double grossIncome) {
        // Initialize PRSI rate
        double prsiRate = 0.0;

        // Determine PRSI rate based on income threshold
        if (grossIncome > 18354.30) {
            prsiRate = 0.04;
        }

        // Calculate and return PRSI
        return grossIncome * prsiRate;
    }

    // Method to calculate the total tax (Income Tax + USC + PRSI) based on gross income and tax credits
    @Override
    public double calculateTotalTax(double grossIncome, double taxCredits) {
        // Calculate individual components of the total tax
        double incomeTax = calculateIncomeTax(grossIncome, taxCredits);
        double usc = calculateUSC(grossIncome);
        double prsi = calculatePRSI(grossIncome);

        // Return the sum of income tax, USC, and PRSI
        return incomeTax + usc + prsi;
    }
}