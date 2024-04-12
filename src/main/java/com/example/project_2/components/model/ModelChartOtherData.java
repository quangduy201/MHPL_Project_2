/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.components.model;

/**
 *
 * @author Hung
 */
public class ModelChartOtherData {
    private String label;
    private int countTotal;
    private double priceTotal;

    public ModelChartOtherData(String label, int countTotal, double priceTotal) {
        this.label = label;
        this.countTotal = countTotal;
        this.priceTotal = priceTotal;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getCountTotal() {
        return countTotal;
    }

    public void setCountTotal(int countTotal) {
        this.countTotal = countTotal;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

    
}
