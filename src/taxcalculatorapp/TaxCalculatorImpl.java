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

        double payeRate;
        if (grossIncome <= 40000) {
            payeRate = 0.2;
        } else {
            payeRate = 0.4;
        }
        return taxableIncome * payeRate;
    }

    @Override
    public double calculateUSC(double grossIncome) {
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

        return grossIncome * uscRate;
    }

    @Override
    public double calculatePRSI(double grossIncome) {
        double prsiRate = 0.0;

        if (grossIncome > 18354.30) {
            prsiRate = 0.04;
        }

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