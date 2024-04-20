package com.example.project_2.components.date_chooser;

import javax.swing.*;

public final class Years extends javax.swing.JPanel {

    private Event event;
    private int startYear;

    public Years() {
        initComponents();
    }

    public int showYear(int year) {
        year = calculateYear(year);
        for (int i = 0; i < getComponentCount(); i++) {
            JButton cmd = (JButton) getComponent(i);
            cmd.setText(year + "");
            year++;
        }
        return startYear;
    }

    private int calculateYear(int year) {
        year -= year % 10;
        startYear = year;
        return year;
    }

    private void addEvent() {
        for (int i = 0; i < getComponentCount(); i++) {
            ((DateChooserButton) getComponent(i)).setEvent(event);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmd2 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd1 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd3 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd6 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd5 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd4 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd7 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd9 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd8 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd11 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd10 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd12 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd21 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd22 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd23 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd24 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd25 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd26 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd27 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd28 = new com.example.project_2.components.date_chooser.DateChooserButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridLayout(5, 4));

        cmd2.setText("2010");
        cmd2.setName("year"); // NOI18N
        cmd2.setPaintBackground(false);
        add(cmd2);

        cmd1.setText("2011");
        cmd1.setName("year"); // NOI18N
        cmd1.setPaintBackground(false);
        add(cmd1);

        cmd3.setText("2012");
        cmd3.setName("year"); // NOI18N
        cmd3.setPaintBackground(false);
        add(cmd3);

        cmd6.setText("2013");
        cmd6.setName("year"); // NOI18N
        cmd6.setPaintBackground(false);
        add(cmd6);

        cmd5.setText("2014");
        cmd5.setName("year"); // NOI18N
        cmd5.setPaintBackground(false);
        add(cmd5);

        cmd4.setText("2015");
        cmd4.setName("year"); // NOI18N
        cmd4.setPaintBackground(false);
        add(cmd4);

        cmd7.setText("2016");
        cmd7.setName("year"); // NOI18N
        cmd7.setPaintBackground(false);
        add(cmd7);

        cmd9.setText("2017");
        cmd9.setName("year"); // NOI18N
        cmd9.setPaintBackground(false);
        add(cmd9);

        cmd8.setText("2018");
        cmd8.setName("year"); // NOI18N
        cmd8.setPaintBackground(false);
        add(cmd8);

        cmd11.setText("2019");
        cmd11.setName("year"); // NOI18N
        cmd11.setPaintBackground(false);
        add(cmd11);

        cmd10.setText("2020");
        cmd10.setName("year"); // NOI18N
        cmd10.setPaintBackground(false);
        add(cmd10);

        cmd12.setText("2021");
        cmd12.setName("year"); // NOI18N
        cmd12.setPaintBackground(false);
        add(cmd12);

        cmd21.setText("2022");
        cmd21.setName("year"); // NOI18N
        cmd21.setPaintBackground(false);
        add(cmd21);

        cmd22.setText("2023");
        cmd22.setName("year"); // NOI18N
        cmd22.setPaintBackground(false);
        add(cmd22);

        cmd23.setText("2024");
        cmd23.setName("year"); // NOI18N
        cmd23.setPaintBackground(false);
        add(cmd23);

        cmd24.setText("2025");
        cmd24.setName("year"); // NOI18N
        cmd24.setPaintBackground(false);
        add(cmd24);

        cmd25.setText("2026");
        cmd25.setName("year"); // NOI18N
        cmd25.setPaintBackground(false);
        add(cmd25);

        cmd26.setText("2027");
        cmd26.setName("year"); // NOI18N
        cmd26.setPaintBackground(false);
        add(cmd26);

        cmd27.setText("2028");
        cmd27.setName("year"); // NOI18N
        cmd27.setPaintBackground(false);
        add(cmd27);

        cmd28.setText("2029");
        cmd28.setName("year"); // NOI18N
        cmd28.setPaintBackground(false);
        add(cmd28);
    }// </editor-fold>//GEN-END:initComponents

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
        addEvent();
    }

    public int next(int year) {
        showYear(year + 20);
        return startYear;
    }

    public int back(int year) {
        showYear(year - 20);
        return startYear;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.example.project_2.components.date_chooser.DateChooserButton cmd1;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd10;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd11;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd12;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd2;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd21;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd22;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd23;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd24;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd25;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd26;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd27;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd28;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd3;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd4;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd5;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd6;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd7;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd8;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd9;
    // End of variables declaration//GEN-END:variables

}
