/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.example.project_2.GUI;

import com.example.project_2.BLL.ThanhVienBLL;
import com.example.project_2.components.charts.ModelCurveLineChart;
import com.example.project_2.components.date_range_chooser.DateChooserAction;
import com.example.project_2.components.date_range_chooser.DateChooserAdapter;
import com.example.project_2.components.date_range_chooser.DateRangeBetween;
import com.example.project_2.components.date_range_chooser.DateRangeChooser;
import com.example.project_2.components.model.ModelThanhVien;
import java.awt.Color;
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
 * @author Hung
 */
public class ThongKeThanhVienGUI extends javax.swing.JPanel {
    private ThanhVienBLL tvBLL = new ThanhVienBLL();
    private DateRangeChooser dateRangeChooser = new DateRangeChooser();

    /**
     * Creates new form ThongKeGUI
     */
    public ThongKeThanhVienGUI() {
        initComponents();
        setOpaque(false);
        
        chart.setTitle("Chart Data");
        chart.addLegend("Số lượng thành viên", Color.decode("#bbf7d0"), Color.decode("#4ade80"));
        
//        chart.addLegend("Thiết bị được mượn", Color.decode("#a5f3fc"), Color.decode("#22d3ee"));
//        chart.addLegend("Thiết bị đang được mượn", Color.decode("#c7d2fe"), Color.decode("#818cf8"));
//        chart.addLegend("Xử lý vi phạm", Color.decode("#fecaca"), Color.decode("#f87171"));
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
            }
        });
        
        setKhoaCbx();
        setNganhCbx();
        
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minus(Period.ofDays(30));
        setData(startTime, endTime);
    }
    
    private void setData(LocalDateTime startTime, LocalDateTime endTime) {
        
        
        List<Object[]> data = tvBLL.thongKeSoLuongThanhVien(
                startTime,
                endTime,
                khoaCbx.getSelectedItem().toString().equals("Tất cả khoa") ? "" : khoaCbx.getSelectedItem().toString(),
                nganhCbx.getSelectedItem().toString().equals("Tất cả ngành") ? "" : nganhCbx.getSelectedItem().toString()
        );
        
        List<ModelThanhVien> tvList = new ArrayList<>();
        
        System.out.println(data.size());

        for (Object[] o : data) {
            tvList.add(new ModelThanhVien(o[0].toString(), Integer.parseInt(o[1].toString())));
        }
        
        for (int i = tvList.size() - 1; i >= 0; i--) {
            ModelThanhVien tv = tvList.get(i);
            chart.addData(new ModelCurveLineChart(tv.getKhoa(), new double[] {tv.getTotal()}));
        }
        
        
        
        if (tvList.size() == 1) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedEndTime = endTime.format(formatter);
            chart.addData(new ModelCurveLineChart(formattedEndTime, new double[] {0}));
        }
        
        chart.start();
    }
    
    private void setKhoaCbx() {
        khoaCbx.addItem("Tất cả khoa");
        
        List<String> khoaList = tvBLL.layDanhSachKhoa();
        
        for (String k : khoaList) {
            khoaCbx.addItem(k);
        }
        
        khoaCbx.setSelectedIndex(0);
    }
    
    private void setNganhCbx() {
        nganhCbx.addItem("Tất cả ngành");
        
        List<String> khoaList = tvBLL.layDanhSachKhoa();
        
        for (String k : khoaList) {
            nganhCbx.addItem(k);
        }
        
        nganhCbx.setSelectedIndex(0);
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
        nganhCbx = new com.example.project_2.components.combobox.ComboBoxSuggestion();
        khoaCbx = new com.example.project_2.components.combobox.ComboBoxSuggestion();
        chart = new com.example.project_2.components.charts.CurveLineChart();

        setAutoscrolls(true);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(159, 159, 159));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Số lượng sinh viên vào khu vực học tập");

        jLabel2.setText("Lọc theo ngày:");

        chart.setFillColor(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 863, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(chDate, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nganhCbx, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(khoaCbx, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 866, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nganhCbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(khoaCbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.example.project_2.components.swing.TextField chDate;
    private com.example.project_2.components.charts.CurveLineChart chart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private com.example.project_2.components.combobox.ComboBoxSuggestion khoaCbx;
    private com.example.project_2.components.combobox.ComboBoxSuggestion nganhCbx;
    // End of variables declaration//GEN-END:variables
}
