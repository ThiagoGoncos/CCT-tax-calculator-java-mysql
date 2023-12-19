/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

/**
 *
 * @author kelvindumas
 */
class TaxCalculatorImpl implements TaxCalculator {
    @Override
    public double calculateIncomeTax(double grossIncome, double taxCredits) {
        double taxableIncome = grossIncome - taxCredits;
        double incomeTaxRate = 0.20;
        return taxableIncome * incomeTaxRate;
    }

    @Override
    public double calculateUSC(double grossIncome) {
        double uscRate = 0.05;
        return grossIncome * uscRate;
    }

    @Override
    public double calculatePRSI(double grossIncome) {
        double prsiRate = 0.03;
        return grossIncome * prsiRate;
    }

    @Override
    public double calculateTotalTax(double grossIncome, double taxCredits) {
        double incomeTax = calculateIncomeTax(grossIncome, taxCredits);
        double usc = calculateUSC(grossIncome);
        double prsi = calculatePRSI(grossIncome);
        return incomeTax + usc + prsi;
    }
}
