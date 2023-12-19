/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculatorapp;

// Definition of an interface named TaxCalculator
interface TaxCalculator {

    // Method to calculate income tax based on gross income and tax credits
    double calculateIncomeTax(double grossIncome, double taxCredits);

    // Method to calculate Universal Social Charge (USC) based on gross income
    double calculateUSC(double grossIncome);

    // Method to calculate Pay-Related Social Insurance (PRSI) based on gross income
    double calculatePRSI(double grossIncome);

    // Method to calculate the total tax amount based on gross income and tax credits
    double calculateTotalTax(double grossIncome, double taxCredits);
}