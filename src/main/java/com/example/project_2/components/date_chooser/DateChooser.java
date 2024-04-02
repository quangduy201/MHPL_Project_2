package com.example.project_2.components.date_chooser;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public final class DateChooser extends javax.swing.JPanel {

    public JTextField getTextRefernce() {
        return textRefernce;
    }

    public void addEventDateChooser(EventDateChooser event) {
        events.add(event);
    }

    private JTextField textRefernce;
    private final String MONTH_ENGLISH[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private String dateFormat = "dd-MM-yyyy";
    private int MONTH = 1;
    private int YEAR = 2021;
    private int DAY = 1;
    private int STATUS = 1;   //  1 is day    2 is month  3 is year
    private int startYear;
    private SelectedDate selectedDate = new SelectedDate();
    private List<EventDateChooser> events;

    public DateChooser() {
        initComponents();
        execute();
    }

    private void execute() {
        setForeground(new Color(204, 93, 93));
        events = new ArrayList<>();
        popup.add(this);
        toDay(false);
    }

    public void setTextRefernce(JTextField txt) {
        this.textRefernce = txt;
        this.textRefernce.setEditable(false);
        this.textRefernce.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (textRefernce.isEnabled()) {
                    showPopup();
                }
            }
        });
        setText(false, 0);
    }

    private void setText(boolean runEvent, int act) {
        if (textRefernce != null) {
            try {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                Date date = df.parse(DAY + "-" + MONTH + "-" + YEAR);
                textRefernce.setText(new SimpleDateFormat(dateFormat).format(date));
            } catch (ParseException e) {
                System.err.println(e);
            }
        }
        if (runEvent) {
            runEvent(act);
        }
    }

    private void runEvent(int act) {
        SelectedAction action = new SelectedAction() {
            @Override
            public int getAction() {
                return act;
            }
        };
        for (EventDateChooser event : events) {
            event.dateSelected(action, selectedDate);
        }
    }

    private Event getEventDay(Dates dates) {
        return (MouseEvent evt, int num) -> {
            dates.clearSelected();
            dates.setSelected(num);
            DAY = num;
            selectedDate.setDay(DAY);
            selectedDate.setMonth(MONTH);
            selectedDate.setYear(YEAR);
            setText(true, 1);
            if (evt != null && evt.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(evt)) {
                popup.setVisible(false);
            }
        };
    }

    private Event getEventMonth() {
        return (MouseEvent evt, int num) -> {
            MONTH = num;
            selectedDate.setDay(DAY);
            selectedDate.setMonth(MONTH);
            selectedDate.setYear(YEAR);
            setText(true, 2);
            Dates d = new Dates();
            d.setForeground(getForeground());
            d.setEvent(getEventDay(d));
            d.showDate(MONTH, YEAR, selectedDate);
//            if (slide.slideToDown(d)) {
//                cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
//                cmdYear.setText(YEAR + "");
//                STATUS = 1;
//            }
        };
    }

    private Event getEventYear() {
        return (MouseEvent evt, int num) -> {
            YEAR = num;
            selectedDate.setDay(DAY);
            selectedDate.setMonth(MONTH);
            selectedDate.setYear(YEAR);
            setText(true, 3);
            Months d = new Months();
            d.setEvent(getEventMonth());
//            if (slide.slideToDown(d)) {
//                cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
//                cmdYear.setText(YEAR + "");
//                STATUS = 2;
//            }
        };
    }

    private void toDay(boolean runEvent) {
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String toDay = df.format(date);
        DAY = Integer.valueOf(toDay.split("-")[0]);
        MONTH = Integer.valueOf(toDay.split("-")[1]);
        YEAR = Integer.valueOf(toDay.split("-")[2]);
        selectedDate.setDay(DAY);
        selectedDate.setMonth(MONTH);
        selectedDate.setYear(YEAR);
        dates.showDate(MONTH, YEAR, selectedDate);
//        slide.slideNon(dates);
//        cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
//        cmdYear.setText(YEAR + "");
        setText(runEvent, 0);
    }

    public void toDay() {
        toDay(true);
    }

    private void setDateNext() {
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        dates.showDate(MONTH, YEAR, selectedDate);
//        if (slide.slideToLeft(dates)) {
//            cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
//            cmdYear.setText(YEAR + "");
//        }
    }

    private void setDateBack() {
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        dates.showDate(MONTH, YEAR, selectedDate);
//        if (slide.slideToRight(dates)) {
//            cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
//            cmdYear.setText(YEAR + "");
//        }
    }

    private void setYearNext() {
        Years years = new Years();
        years.setEvent(getEventYear());
        startYear = years.next(startYear);
//        slide.slideToLeft(years);
    }

    private void setYearBack() {
        if (startYear >= 1000) {
            Years years = new Years();
            years.setEvent(getEventYear());
            startYear = years.back(startYear);
//            slide.slideToLeft(years);
        }
    }

    public void showPopup(Component com, int x, int y) {
        popup.show(com, x, y);
    }

    public void showPopup() {
        popup.show(textRefernce, 0, textRefernce.getHeight());
    }

    public void hidePopup() {
        popup.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popup = new javax.swing.JPopupMenu(){
            @Override
            protected void paintComponent(Graphics grphcs) {
                grphcs.setColor(new Color(114, 113, 113));
                grphcs.fillRect(0, 0, getWidth(), getHeight());
                grphcs.setColor(Color.WHITE);
                grphcs.fillRect(1, 1, getWidth() - 2, getHeight() - 2);
            }
        };
        header = new javax.swing.JPanel();
        cmdForward = new com.example.project_2.components.date_chooser.DateChooserButton();
        MY = new javax.swing.JLayeredPane();
        cmdMonth = new com.example.project_2.components.date_chooser.DateChooserButton();
        lb = new javax.swing.JLabel();
        cmdYear = new com.example.project_2.components.date_chooser.DateChooserButton();
        cmdPrevious = new com.example.project_2.components.date_chooser.DateChooserButton();
        slider = new com.example.project_2.components.date_chooser.Slider();

        setBackground(new java.awt.Color(255, 255, 255));

        header.setBackground(new java.awt.Color(204, 93, 93));
        header.setMaximumSize(new java.awt.Dimension(262, 40));

        cmdForward.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/forward.png"))); // NOI18N
        cmdForward.setPaintBackground(false);
        cmdForward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdForwardActionPerformed(evt);
            }
        });

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0);
        flowLayout1.setAlignOnBaseline(true);
        MY.setLayout(flowLayout1);

        cmdMonth.setForeground(new java.awt.Color(255, 255, 255));
        cmdMonth.setText("January");
        cmdMonth.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cmdMonth.setPaintBackground(false);
        MY.add(cmdMonth);

        lb.setForeground(new java.awt.Color(255, 255, 255));
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("-");
        MY.add(lb);

        cmdYear.setForeground(new java.awt.Color(255, 255, 255));
        cmdYear.setText("2018");
        cmdYear.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cmdYear.setPaintBackground(false);
        MY.add(cmdYear);

        cmdPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/previous.png"))); // NOI18N
        cmdPrevious.setPaintBackground(false);
        cmdPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPreviousActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(MY, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(cmdForward, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmdForward, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(slider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(slider, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdForwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdForwardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdForwardActionPerformed

    private void cmdPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPreviousActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdPreviousActionPerformed

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setSelectedDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String d = df.format(date);
        DAY = Integer.valueOf(d.split("-")[0]);
        MONTH = Integer.valueOf(d.split("-")[1]);
        YEAR = Integer.valueOf(d.split("-")[2]);
        selectedDate.setDay(DAY);
        selectedDate.setMonth(MONTH);
        selectedDate.setYear(YEAR);
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        dates.setSelected(DAY);
        dates.showDate(MONTH, YEAR, selectedDate);
//        slide.slideNon(dates);
//        cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
//        cmdYear.setText(YEAR + "");
        setText(true, 0);
        STATUS = 1;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane MY;
    private com.example.project_2.components.date_chooser.DateChooserButton cmdForward;
    private com.example.project_2.components.date_chooser.DateChooserButton cmdMonth;
    private com.example.project_2.components.date_chooser.DateChooserButton cmdPrevious;
    private com.example.project_2.components.date_chooser.DateChooserButton cmdYear;
    private javax.swing.JPanel header;
    private javax.swing.JLabel lb;
    private javax.swing.JPopupMenu popup;
    private com.example.project_2.components.date_chooser.Slider slider;
    // End of variables declaration//GEN-END:variables

    public SelectedDate getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(SelectedDate selectedDate) {
        this.selectedDate = selectedDate;
        DAY = selectedDate.getDay();
        MONTH = selectedDate.getMonth();
        YEAR = selectedDate.getYear();
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        dates.setSelected(DAY);
        dates.showDate(MONTH, YEAR, selectedDate);
//        slide.slideNon(dates);
//        cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
//        cmdYear.setText(YEAR + "");
        setText(true, 0);
        STATUS = 1;
    }

    @Override
    public void setForeground(Color color) {
        super.setForeground(color);
        if (header != null) {
            header.setBackground(color);
            toDay(false);
        }
    }
}
