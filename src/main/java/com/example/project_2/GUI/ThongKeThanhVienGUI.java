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
        
        table1.fixTable(jScrollPane1);
        
        chart.setTitle("Thống kê số lượng thành viên vào khu học tập");
        chart.addLegend("Số lượng thành viên", Color.decode("#bbf7d0"), Color.decode("#4ade80"));
        
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
        
        // Tạm thời gỡ bỏ bộ lắng nghe sự kiện ItemListener
        khoaCbx.removeItemListener(khoaItemListener);
        nganhCbx.removeItemListener(nganhItemListener);
        
        setKhoaCbx();
        setNganhCbx();
        
        // Thêm lắng nghe sự kiện ItemListener
        khoaCbx.addItemListener(khoaItemListener);
        nganhCbx.addItemListener(nganhItemListener);
        
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minus(Period.ofDays(30));
        
        setData(startTime, endTime);
        setDataForTable(startTime, endTime);
    }
    
    private void setData(LocalDateTime startTime, LocalDateTime endTime) {
        chart.clear();
        
        List<Object[]> data = tvBLL.thongKeSoLuongThanhVien(
                startTime,
                endTime,
                khoaCbx.getSelectedItem().toString().equals("Tất cả khoa") ? "" : khoaCbx.getSelectedItem().toString(),
                nganhCbx.getSelectedItem().toString().equals("Tất cả ngành") ? "" : nganhCbx.getSelectedItem().toString()
        );
        
        List<ModelChartData> tvList = new ArrayList<>();
        
        for (Object[] o : data) {
            System.out.println(o[0].toString() + " - " + o[1].toString());
            tvList.add(new ModelChartData(o[0].toString(), Integer.parseInt(o[1].toString())));
        }
        
        for (int i = 0; i < tvList.size(); i++) {
            ModelChartData tv = tvList.get(i);
            chart.addData(new ModelCurveLineChart(tv.getLabel(), new double[] {tv.getTotal()}));
        }
        
        if (tvList.size() == 1) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedEndTime = endTime.format(formatter);
            chart.addData(new ModelCurveLineChart(formattedEndTime, new double[] {0}));
        }
        
        chart.start();
    }
    
    private void setDataForTable(LocalDateTime startTime, LocalDateTime endTime) {
        List<Object[]> data = tvBLL.thongKeSoLuongThanhVienForTable(
                startTime,
                endTime,
                khoaCbx.getSelectedItem().toString().equals("Tất cả khoa") ? "" : khoaCbx.getSelectedItem().toString(),
                nganhCbx.getSelectedItem().toString().equals("Tất cả ngành") ? "" : nganhCbx.getSelectedItem().toString()
        );
        
        table1.removeAllRow();

        for (Object[] o : data) {
            table1.addRow(o);
        }
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
    
    // Bộ lắng nghe sự kiện ItemListener cho khoaCbx
    private final ItemListener khoaItemListener = (ItemEvent evt) -> {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            // Xử lý khi giá trị được chọn thay đổi
            khoaCbxItemStateChanged(evt);
        }
    };

    // Bộ lắng nghe sự kiện ItemListener cho nganhCbx
    private final ItemListener nganhItemListener = (ItemEvent evt) -> {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            // Xử lý khi giá trị được chọn thay đổi
            nganhCbxItemStateChanged(evt);
        }
    };
    
    private void nganhCbxItemStateChanged(ItemEvent evt) {                                          
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            DateRangeBetween dateRangeBetween = dateRangeChooser.getSelectedDateBetween();

            DateRange dateRange = Helper.convertDateBetweenToLocalDateTime(dateRangeBetween);

            setData(dateRange.startTime, dateRange.endTime);
            setDataForTable(dateRange.startTime, dateRange.endTime);
        }
    }                                         

    private void khoaCbxItemStateChanged(ItemEvent evt) {                                         
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            DateRangeBetween dateRangeBetween = dateRangeChooser.getSelectedDateBetween();

            DateRange dateRange = Helper.convertDateBetweenToLocalDateTime(dateRangeBetween);

            setData(dateRange.startTime, dateRange.endTime);
            setDataForTable(dateRange.startTime, dateRange.endTime);
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.example.project_2.components.table.Table();

        setBackground(new java.awt.Color(255, 255, 255));
        setAutoscrolls(true);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(159, 159, 159));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Số lượng sinh viên vào khu vực học tập");

        jLabel2.setText("Lọc theo ngày:");

        chDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chDateActionPerformed(evt);
            }
        });

        chart.setFillColor(true);

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Thành Viên", "Tên Thành Viên", "Khoa", "Ngành", "Ngày giờ vào"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table1);
        if (table1.getColumnModel().getColumnCount() > 0) {
            table1.getColumnModel().getColumn(0).setResizable(false);
            table1.getColumnModel().getColumn(1).setResizable(false);
            table1.getColumnModel().getColumn(2).setResizable(false);
            table1.getColumnModel().getColumn(3).setResizable(false);
            table1.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chDate, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 285, Short.MAX_VALUE)
                        .addComponent(nganhCbx, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(khoaCbx, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chDateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.example.project_2.components.swing.TextField chDate;
    private com.example.project_2.components.charts.CurveLineChart chart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.example.project_2.components.combobox.ComboBoxSuggestion khoaCbx;
    private com.example.project_2.components.combobox.ComboBoxSuggestion nganhCbx;
    private com.example.project_2.components.table.Table table1;
    // End of variables declaration//GEN-END:variables
}
