package com.example.project_2.components.date_chooser;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;

public final class Dates extends javax.swing.JPanel {

    private Event event;
    private final int MONTH;
    private final int YEAR;
    private final int DAY;
    private int m;
    private int y;
    private int selectDay = 0;
    private int startDate;
    private int max_of_month;

    public Dates() {
        initComponents();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String toDay = df.format(date);
        DAY = Integer.valueOf(toDay.split("-")[0]);
        MONTH = Integer.valueOf(toDay.split("-")[1]);
        YEAR = Integer.valueOf(toDay.split("-")[2]);
    }

    public void showDate(int month, int year, SelectedDate select) {
        m = month;
        y = year;
        // selectDay = 0;
        Calendar cd = Calendar.getInstance();
        cd.set(year, month - 1, 1);
        int start = cd.get(Calendar.DAY_OF_WEEK);
        max_of_month = cd.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (start == 1) {
            start += 7;
        }
        clear();
        start += 5;
        startDate = start;
        for (int i = 1; i <= max_of_month; i++) {
            DateChooserButton cmd = (DateChooserButton) getComponent(start);
            cmd.setColorSelected(getForeground());
            cmd.setText(i + "");
            if (i == DAY && month == MONTH && year == YEAR) {
                cmd.setBackground(new Color(224, 214, 229));
            } else {
                cmd.setBackground(Color.WHITE);
            }
            if (i == select.getDay() && month == select.getMonth() && year == select.getYear()) {
                cmd.setBackground(getForeground());
                cmd.setForeground(new Color(255, 255, 255));
            }
            start++;
        }
    }

    private void clear() {
        for (int i = 7; i < getComponentCount(); i++) {
            ((JButton) getComponent(i)).setText("");
        }
    }

    public void clearSelected() {
        for (int i = 7; i < getComponentCount(); i++) {
            JButton cmd = (JButton) getComponent(i);
            if (MONTH == m && y == YEAR && !cmd.getText().equals("") && Integer.valueOf(cmd.getText()) == DAY) {
                cmd.setBackground(new Color(224, 214, 229));
                cmd.setForeground(new java.awt.Color(75, 75, 75));
            } else {
                cmd.setBackground(Color.WHITE);
                cmd.setForeground(new java.awt.Color(75, 75, 75));
            }
        }
        selectDay = 0;
    }

    private void addEvent() {
        for (int i = 7; i < getComponentCount(); i++) {
            ((DateChooserButton) getComponent(i)).setEvent(event);
        }
    }

    public void setSelected(int index) {
        selectDay = index;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdMo = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmdTu = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmdWe = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmdTh = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmdFr = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmdSa = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmdSu = new com.example.project_2.components.date_chooser.DateChooserButton();
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
        cmd13 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd14 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd15 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd16 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd17 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd18 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd19 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd20 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd21 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd22 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd23 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd24 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd25 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd26 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd27 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd28 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd29 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd30 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd31 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd32 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd33 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd34 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd35 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd36 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd37 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd38 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd39 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd40 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd41 = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmd42 = new com.example.project_2.components.date_chooser.DateChooserButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridLayout(7, 7));

        cmdMo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));
        cmdMo.setForeground(new java.awt.Color(118, 118, 118));
        cmdMo.setText("Mo");
        add(cmdMo);

        cmdTu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));
        cmdTu.setForeground(new java.awt.Color(118, 118, 118));
        cmdTu.setText("Tu");
        add(cmdTu);

        cmdWe.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));
        cmdWe.setForeground(new java.awt.Color(118, 118, 118));
        cmdWe.setText("We");
        add(cmdWe);

        cmdTh.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));
        cmdTh.setForeground(new java.awt.Color(118, 118, 118));
        cmdTh.setText("Th");
        add(cmdTh);

        cmdFr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));
        cmdFr.setForeground(new java.awt.Color(118, 118, 118));
        cmdFr.setText("Fr");
        add(cmdFr);

        cmdSa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));
        cmdSa.setForeground(new java.awt.Color(118, 118, 118));
        cmdSa.setText("Sa");
        add(cmdSa);

        cmdSu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));
        cmdSu.setForeground(new java.awt.Color(255, 1, 1));
        cmdSu.setText("Su");
        add(cmdSu);

        cmd1.setBackground(new java.awt.Color(255, 255, 255));
        cmd1.setForeground(new java.awt.Color(75, 75, 75));
        cmd1.setName("day"); // NOI18N
        add(cmd1);

        cmd2.setBackground(new java.awt.Color(255, 255, 255));
        cmd2.setForeground(new java.awt.Color(75, 75, 75));
        cmd2.setName("day"); // NOI18N
        add(cmd2);

        cmd3.setBackground(new java.awt.Color(255, 255, 255));
        cmd3.setForeground(new java.awt.Color(75, 75, 75));
        cmd3.setText("1");
        cmd3.setName("day"); // NOI18N
        add(cmd3);

        cmd4.setBackground(new java.awt.Color(255, 255, 255));
        cmd4.setForeground(new java.awt.Color(75, 75, 75));
        cmd4.setText("2");
        cmd4.setName("day"); // NOI18N
        add(cmd4);

        cmd5.setBackground(new java.awt.Color(255, 255, 255));
        cmd5.setForeground(new java.awt.Color(75, 75, 75));
        cmd5.setText("3");
        cmd5.setName("day"); // NOI18N
        add(cmd5);

        cmd6.setBackground(new java.awt.Color(255, 255, 255));
        cmd6.setForeground(new java.awt.Color(75, 75, 75));
        cmd6.setText("4");
        cmd6.setName("day"); // NOI18N
        add(cmd6);

        cmd7.setBackground(new java.awt.Color(255, 255, 255));
        cmd7.setForeground(new java.awt.Color(75, 75, 75));
        cmd7.setText("5");
        cmd7.setName("day"); // NOI18N
        add(cmd7);

        cmd8.setBackground(new java.awt.Color(255, 255, 255));
        cmd8.setForeground(new java.awt.Color(75, 75, 75));
        cmd8.setText("6");
        cmd8.setName("day"); // NOI18N
        add(cmd8);

        cmd9.setBackground(new java.awt.Color(255, 255, 255));
        cmd9.setForeground(new java.awt.Color(75, 75, 75));
        cmd9.setText("7");
        cmd9.setName("day"); // NOI18N
        add(cmd9);

        cmd10.setBackground(new java.awt.Color(255, 255, 255));
        cmd10.setForeground(new java.awt.Color(75, 75, 75));
        cmd10.setText("8");
        cmd10.setName("day"); // NOI18N
        add(cmd10);

        cmd11.setBackground(new java.awt.Color(255, 255, 255));
        cmd11.setForeground(new java.awt.Color(75, 75, 75));
        cmd11.setText("9");
        cmd11.setName("day"); // NOI18N
        add(cmd11);

        cmd12.setBackground(new java.awt.Color(255, 255, 255));
        cmd12.setForeground(new java.awt.Color(75, 75, 75));
        cmd12.setText("10");
        cmd12.setName("day"); // NOI18N
        add(cmd12);

        cmd13.setBackground(new java.awt.Color(255, 255, 255));
        cmd13.setForeground(new java.awt.Color(75, 75, 75));
        cmd13.setText("11");
        cmd13.setName("day"); // NOI18N
        add(cmd13);

        cmd14.setBackground(new java.awt.Color(255, 255, 255));
        cmd14.setForeground(new java.awt.Color(75, 75, 75));
        cmd14.setText("12");
        cmd14.setName("day"); // NOI18N
        add(cmd14);

        cmd15.setBackground(new java.awt.Color(255, 255, 255));
        cmd15.setForeground(new java.awt.Color(75, 75, 75));
        cmd15.setText("13");
        cmd15.setName("day"); // NOI18N
        add(cmd15);

        cmd16.setBackground(new java.awt.Color(255, 255, 255));
        cmd16.setForeground(new java.awt.Color(75, 75, 75));
        cmd16.setText("14");
        cmd16.setName("day"); // NOI18N
        add(cmd16);

        cmd17.setBackground(new java.awt.Color(255, 255, 255));
        cmd17.setForeground(new java.awt.Color(75, 75, 75));
        cmd17.setText("15");
        cmd17.setName("day"); // NOI18N
        add(cmd17);

        cmd18.setBackground(new java.awt.Color(255, 255, 255));
        cmd18.setForeground(new java.awt.Color(75, 75, 75));
        cmd18.setText("16");
        cmd18.setName("day"); // NOI18N
        add(cmd18);

        cmd19.setBackground(new java.awt.Color(255, 255, 255));
        cmd19.setForeground(new java.awt.Color(75, 75, 75));
        cmd19.setText("17");
        cmd19.setName("day"); // NOI18N
        add(cmd19);

        cmd20.setBackground(new java.awt.Color(255, 255, 255));
        cmd20.setForeground(new java.awt.Color(75, 75, 75));
        cmd20.setText("18");
        cmd20.setName("day"); // NOI18N
        add(cmd20);

        cmd21.setBackground(new java.awt.Color(255, 255, 255));
        cmd21.setForeground(new java.awt.Color(75, 75, 75));
        cmd21.setText("19");
        cmd21.setName("day"); // NOI18N
        add(cmd21);

        cmd22.setBackground(new java.awt.Color(255, 255, 255));
        cmd22.setForeground(new java.awt.Color(75, 75, 75));
        cmd22.setText("20");
        cmd22.setName("day"); // NOI18N
        add(cmd22);

        cmd23.setBackground(new java.awt.Color(255, 255, 255));
        cmd23.setForeground(new java.awt.Color(75, 75, 75));
        cmd23.setText("21");
        cmd23.setName("day"); // NOI18N
        add(cmd23);

        cmd24.setBackground(new java.awt.Color(255, 255, 255));
        cmd24.setForeground(new java.awt.Color(75, 75, 75));
        cmd24.setText("22");
        cmd24.setName("day"); // NOI18N
        add(cmd24);

        cmd25.setBackground(new java.awt.Color(255, 255, 255));
        cmd25.setForeground(new java.awt.Color(75, 75, 75));
        cmd25.setText("23");
        cmd25.setName("day"); // NOI18N
        add(cmd25);

        cmd26.setBackground(new java.awt.Color(255, 255, 255));
        cmd26.setForeground(new java.awt.Color(75, 75, 75));
        cmd26.setText("24");
        cmd26.setName("day"); // NOI18N
        add(cmd26);

        cmd27.setBackground(new java.awt.Color(255, 255, 255));
        cmd27.setForeground(new java.awt.Color(75, 75, 75));
        cmd27.setText("25");
        cmd27.setName("day"); // NOI18N
        add(cmd27);

        cmd28.setBackground(new java.awt.Color(255, 255, 255));
        cmd28.setForeground(new java.awt.Color(75, 75, 75));
        cmd28.setText("26");
        cmd28.setName("day"); // NOI18N
        add(cmd28);

        cmd29.setBackground(new java.awt.Color(255, 255, 255));
        cmd29.setForeground(new java.awt.Color(75, 75, 75));
        cmd29.setText("27");
        cmd29.setName("day"); // NOI18N
        add(cmd29);

        cmd30.setBackground(new java.awt.Color(255, 255, 255));
        cmd30.setForeground(new java.awt.Color(75, 75, 75));
        cmd30.setText("28");
        cmd30.setName("day"); // NOI18N
        add(cmd30);

        cmd31.setBackground(new java.awt.Color(255, 255, 255));
        cmd31.setForeground(new java.awt.Color(75, 75, 75));
        cmd31.setText("29");
        cmd31.setName("day"); // NOI18N
        add(cmd31);

        cmd32.setBackground(new java.awt.Color(255, 255, 255));
        cmd32.setForeground(new java.awt.Color(75, 75, 75));
        cmd32.setText("30");
        cmd32.setName("day"); // NOI18N
        add(cmd32);

        cmd33.setBackground(new java.awt.Color(255, 255, 255));
        cmd33.setForeground(new java.awt.Color(75, 75, 75));
        cmd33.setText("31");
        cmd33.setName("day"); // NOI18N
        add(cmd33);

        cmd34.setBackground(new java.awt.Color(255, 255, 255));
        cmd34.setForeground(new java.awt.Color(75, 75, 75));
        cmd34.setName("day"); // NOI18N
        add(cmd34);

        cmd35.setBackground(new java.awt.Color(255, 255, 255));
        cmd35.setForeground(new java.awt.Color(75, 75, 75));
        cmd35.setName("day"); // NOI18N
        add(cmd35);

        cmd36.setBackground(new java.awt.Color(255, 255, 255));
        cmd36.setForeground(new java.awt.Color(75, 75, 75));
        cmd36.setName("day"); // NOI18N
        add(cmd36);

        cmd37.setBackground(new java.awt.Color(255, 255, 255));
        cmd37.setForeground(new java.awt.Color(75, 75, 75));
        cmd37.setName("day"); // NOI18N
        add(cmd37);

        cmd38.setBackground(new java.awt.Color(255, 255, 255));
        cmd38.setForeground(new java.awt.Color(75, 75, 75));
        cmd38.setName("day"); // NOI18N
        add(cmd38);

        cmd39.setBackground(new java.awt.Color(255, 255, 255));
        cmd39.setForeground(new java.awt.Color(75, 75, 75));
        cmd39.setName("day"); // NOI18N
        add(cmd39);

        cmd40.setBackground(new java.awt.Color(255, 255, 255));
        cmd40.setForeground(new java.awt.Color(75, 75, 75));
        cmd40.setName("day"); // NOI18N
        add(cmd40);

        cmd41.setBackground(new java.awt.Color(255, 255, 255));
        cmd41.setForeground(new java.awt.Color(75, 75, 75));
        cmd41.setName("day"); // NOI18N
        add(cmd41);

        cmd42.setBackground(new java.awt.Color(255, 255, 255));
        cmd42.setForeground(new java.awt.Color(75, 75, 75));
        cmd42.setName("day"); // NOI18N
        add(cmd42);
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
    private com.example.project_2.components.date_chooser.DateChooserButton cmd13;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd14;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd15;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd16;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd17;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd18;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd19;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd2;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd20;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd21;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd22;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd23;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd24;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd25;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd26;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd27;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd28;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd29;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd3;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd30;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd31;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd32;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd33;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd34;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd35;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd36;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd37;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd38;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd39;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd4;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd40;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd41;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd42;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd5;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd6;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd7;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd8;
    private com.example.project_2.components.date_chooser.DateChooserButton cmd9;
    private com.example.project_2.components.date_chooser.DateChooserButton cmdFr;
    private com.example.project_2.components.date_chooser.DateChooserButton cmdMo;
    private com.example.project_2.components.date_chooser.DateChooserButton cmdSa;
    private com.example.project_2.components.date_chooser.DateChooserButton cmdSu;
    private com.example.project_2.components.date_chooser.DateChooserButton cmdTh;
    private com.example.project_2.components.date_chooser.DateChooserButton cmdTu;
    private com.example.project_2.components.date_chooser.DateChooserButton cmdWe;
    // End of variables declaration//GEN-END:variables

    public void next() {
        if (selectDay == max_of_month) {
            selectDay = 0;
        }
        JButton cmd = (JButton) getComponent(startDate - 1 + selectDay + 1);
        String n = cmd.getText();
        if (!n.equals("") && Integer.valueOf(n) <= max_of_month) {
            selectDay++;
            event.execute(null, selectDay);
            cmd.setBackground(new Color(206, 110, 245));
        }
    }

    public void back() {
        if (selectDay <= 1) {
            selectDay = max_of_month + 1;
        }
        JButton cmd = (JButton) getComponent(startDate - 1 + selectDay - 1);
        String n = cmd.getText();
        if (!n.equals("") && cmd.getName() != null) {
            selectDay--;
            event.execute(null, selectDay);
            cmd.setBackground(new Color(206, 110, 245));
        }
    }

    public void up() {
        JButton cmd = (JButton) getComponent(startDate - 1 + selectDay - 7);
        String n = cmd.getText();
        if (!n.equals("") && cmd.getName() != null) {
            selectDay -= 7;
            event.execute(null, selectDay);
            cmd.setBackground(new Color(206, 110, 245));
        }
    }

    public void down() {
        if (getComponents().length > startDate - 1 + selectDay + 7) {
            JButton cmd = (JButton) getComponent(startDate - 1 + selectDay + 7);
            String n = cmd.getText();
            if (!n.equals("") && cmd.getName() != null) {
                selectDay += 7;
                event.execute(null, selectDay);
                cmd.setBackground(new Color(206, 110, 245));
            }
        }
    }

}
