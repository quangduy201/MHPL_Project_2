package com.example.project_2.components.date_chooser;

public final class Months extends javax.swing.JPanel {

    private Event event;
    private int m;

    public Months() {
        initComponents();
    }

    private void addEvent() {
        for (int i = 0; i < getComponentCount(); i++) {
            ((DateChooserButton) getComponent(i)).setEvent(event);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmd1 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd2 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd3 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd4 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd5 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd6 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd7 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd8 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd9 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd10 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd11 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd12 = new com.example.project_2.components.date_chooser.DateChooserButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridLayout(4, 4));

        cmd1.setText("January");
        cmd1.setName("1"); // NOI18N
        cmd1.setPaintBackground(false);
        add(cmd1);

        cmd2.setText("February");
        cmd2.setName("2"); // NOI18N
        cmd2.setPaintBackground(false);
        add(cmd2);

        cmd3.setText("March");
        cmd3.setName("3"); // NOI18N
        cmd3.setPaintBackground(false);
        add(cmd3);

        cmd4.setText("April");
        cmd4.setName("4"); // NOI18N
        cmd4.setPaintBackground(false);
        add(cmd4);

        cmd5.setText("May");
        cmd5.setName("5"); // NOI18N
        cmd5.setPaintBackground(false);
        add(cmd5);

        cmd6.setText("June");
        cmd6.setName("6"); // NOI18N
        cmd6.setPaintBackground(false);
        add(cmd6);

        cmd7.setText("July");
        cmd7.setName("7"); // NOI18N
        cmd7.setPaintBackground(false);
        add(cmd7);

        cmd8.setText("August");
        cmd8.setName("8"); // NOI18N
        cmd8.setPaintBackground(false);
        add(cmd8);

        cmd9.setText("September");
        cmd9.setName("9"); // NOI18N
        cmd9.setPaintBackground(false);
        add(cmd9);

        cmd10.setText("October");
        cmd10.setName("10"); // NOI18N
        cmd10.setPaintBackground(false);
        add(cmd10);

        cmd11.setText("November");
        cmd11.setName("11"); // NOI18N
        cmd11.setPaintBackground(false);
        add(cmd11);

        cmd12.setText("December");
        cmd12.setName("12"); // NOI18N
        cmd12.setPaintBackground(false);
        add(cmd12);
    }// </editor-fold>//GEN-END:initComponents

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
        addEvent();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.example.project_2.components.date_chooser.DateChooserButton cmd1;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd10;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd11;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd12;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd2;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd3;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd4;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd5;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd6;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd7;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd8;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd9;
    // End of variables declaration//GEN-END:variables

}
