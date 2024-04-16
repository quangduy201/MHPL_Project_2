/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.example.project_2.GUI;

import com.example.project_2.BLL.ThietBiBLL;
import com.example.project_2.components.charts.ModelCurveLineChart;
import com.example.project_2.components.date_range_chooser.DateChooserAction;
import com.example.project_2.components.date_range_chooser.DateChooserAdapter;
import com.example.project_2.components.date_range_chooser.DateRangeBetween;
import com.example.project_2.components.date_range_chooser.DateRangeChooser;
import com.example.project_2.DTO.ThietBi;
import com.example.project_2.components.model.ModelChartData;
import com.example.project_2.utils.DateRange;
import com.example.project_2.utils.Helper;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Dll
 */
public class ThongKeThietBiDangDuocMuonGUI extends javax.swing.JPanel {

    private ThietBiBLL tbBLL = new ThietBiBLL();
    private DateRangeChooser dateRangeChooser = new DateRangeChooser();

    /**
     * Creates new form ThongKeGUI
     */
    public ThongKeThietBiDangDuocMuonGUI() {
        initComponents();
        table.fixTable(jScrollPane1);

        chart.setTitle("Chart Data");
        chart.addLegend("Thiết bị đang mượn", Color.decode("#bbf7d0"), Color.decode("#4ade80"));

        dateRangeChooser.setTextField(chDate);
        dateRangeChooser.setDateSelectionMode(DateRangeChooser.DateSelectionMode.BETWEEN_DATE_SELECTED);

        dateRangeChooser.addActionDateChooserListener(new DateChooserAdapter() {
            @Override
            public void dateBetweenChanged(DateRangeBetween date, DateChooserAction action) {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                String dateFrom = df.format(date.getFromDate());
                String dateTo = df.format(date.getToDate());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate startDate = LocalDate.parse(dateFrom, formatter);
                LocalDate endDate = LocalDate.parse(dateTo, formatter);

                LocalDateTime startTime = startDate.atStartOfDay();
                LocalDateTime endTime = endDate.atTime(LocalTime.MAX);

                chart.clear();

                setData(startTime, endTime);
                setDataForTable(startTime, endTime);
            }

        });
        thietBiCbx.removeItemListener(thietBiItemListener);
        setThietBiCbx();
        thietBiCbx.addItemListener(thietBiItemListener);
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minus(Period.ofDays(30));
        setData(startTime, endTime);
        setDataForTable(startTime, endTime);
    }
    private final ItemListener thietBiItemListener = (ItemEvent evt) -> {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            DateRangeBetween dgb = dateRangeChooser.getSelectedDateBetween();
            DateRange dr = Helper.convertDateBetweenToLocalDateTime(dgb);
            setData(dr.startTime, dr.endTime);
            setDataForTable(dr.startTime, dr.endTime);
         
        }
    };

    private void setData(LocalDateTime startTime, LocalDateTime endTime) {
        chart.clear();
        List<Object[]> data = tbBLL.thongKeThietBiDangDuocMuon(
                startTime,
                endTime,
                thietBiCbx.getSelectedItem().toString().equals("Tất cả thiết bị")
                ? "-1"
                : String.valueOf(((ThietBi) thietBiCbx.getSelectedItem()).getMaTB())
        );

        List<ModelChartData> tvList = new ArrayList<>();

        for (Object[] o : data) {
            tvList.add(new ModelChartData(o[0].toString(), Integer.parseInt(o[1].toString())));
        }

        for (int i = 0; i < tvList.size(); i++) {
            ModelChartData tv = tvList.get(i);
            chart.addData(new ModelCurveLineChart(tv.getLabel(), new double[]{tv.getTotal()}));
        }

        if (tvList.size() == 1) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedEndTime = endTime.format(formatter);
            chart.addData(new ModelCurveLineChart(formattedEndTime, new double[]{0}));
        }

        chart.start();
    }

    private void setThietBiCbx() {
        thietBiCbx.removeAllItems();
        thietBiCbx.addItem(new ThietBi(-1L, "Tất cả thiết bị", ""));

        List<ThietBi> tbList = tbBLL.getAll();

        for (ThietBi tb : tbList) {
            thietBiCbx.addItem(tb);
        }

        thietBiCbx.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        chDate = new com.example.project_2.components.swing.TextField();
        chart = new com.example.project_2.components.charts.CurveLineChart();
        thietBiCbx = new com.example.project_2.components.combobox.ComboBoxSuggestion();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new com.example.project_2.components.table.Table();

        setBackground(new java.awt.Color(255, 255, 255));
        setAutoscrolls(true);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(159, 159, 159));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thiết bị đang mượn");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Lọc theo ngày:");

        chDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chDateActionPerformed(evt);
            }
        });

        chart.setBackground(new java.awt.Color(255, 255, 255));
        chart.setFillColor(true);

        thietBiCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thietBiCbxActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Thiết Bị", "Tên Thiết Bị", "Mã Thành Viên", "Tên Thành Viên", "Ngày giờ mượn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chDate, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(449, 449, 449)
                        .addComponent(thietBiCbx, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addComponent(chart, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(thietBiCbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void thietBiCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thietBiCbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thietBiCbxActionPerformed

    private void chDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chDateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.example.project_2.components.swing.TextField chDate;
    private com.example.project_2.components.charts.CurveLineChart chart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.example.project_2.components.table.Table table;
    private com.example.project_2.components.combobox.ComboBoxSuggestion thietBiCbx;
    // End of variables declaration//GEN-END:variables

    private void setDataForTable(LocalDateTime startTime, LocalDateTime endTime) {
        List<Object[]> data = tbBLL.thongKeThietBiDangDuocMuonForTable(
                startTime,
                endTime,
                thietBiCbx.getSelectedItem().toString().equals("Tất cả thiết bị")
                ? "-1"
                : String.valueOf(((ThietBi) thietBiCbx.getSelectedItem()).getMaTB())
        );

        table.removeAllRow();

        for (Object[] obj : data) {
            table.addRow(obj);
        }

    }
}
