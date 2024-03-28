package com.example.project_2.components.charts;

import java.awt.Color;

public class ModelCurveLineLegend {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public ModelCurveLineLegend(String name, Color color1, Color color2) {
        this.name = name;
        this.color1 = color1;
        this.color2 = color2;
    }

    public ModelCurveLineLegend() {
    }

    private String name;
    private Color color1;
    private Color color2;
}