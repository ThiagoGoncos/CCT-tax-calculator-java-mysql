/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

/**
 *
 * @author kelvindumas
 */
class TaxCalculation {

    private final double grossIncome;
    private final double taxCredits;
    private final double incomeTax;
    private final double usc;
    private final double prsi;

    public TaxCalculation(double grossIncome, double taxCredits, double incomeTax, double usc, double prsi) {
        this.grossIncome = grossIncome;
        this.taxCredits = taxCredits;
        this.incomeTax = incomeTax;
        this.usc = usc;
        this.prsi = prsi;
    }

    public double getGrossIncome() {
        return grossIncome;
    }

    public double getTaxCredits() {
        return taxCredits;
    }

    public double getIncomeTax() {
        return incomeTax;
    }

    public double getUsc() {
        return usc;
    }

    public double getPrsi() {
        return prsi;
    }
}