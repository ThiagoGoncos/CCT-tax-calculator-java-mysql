/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package taxcalculatorapp;

/**
 *
 * @author thiagogoncos
 */
public interface TaxCalculator {
    double calculateIncomeTax(double grossIncome, double taxCredits);
    double calculateUSC(double grossIncome);
    double calculatePRSI(double grossIncome);
    double calculateTotalTax(double grossIncome, double taxCredits);
}
