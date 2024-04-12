/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.example.project_2.GUI;

import com.example.project_2.BLL.XuLyBLL;
import com.example.project_2.components.charts.ModelCurveLineChart;
import com.example.project_2.components.date_range_chooser.DateChooserAction;
import com.example.project_2.components.date_range_chooser.DateChooserAdapter;
import com.example.project_2.components.date_range_chooser.DateRangeBetween;
import com.example.project_2.components.date_range_chooser.DateRangeChooser;
import com.example.project_2.components.model.ModelChartOtherData;
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
public class ThongKeXuLyViPhamGUI extends javax.swing.JPanel {
    private XuLyBLL xlBLL = new XuLyBLL();
    private DateRangeChooser dateRangeChooser = new DateRangeChooser();

    /**
     * Creates new form ThongKeGUI
     */
    public ThongKeXuLyViPhamGUI() {
        initComponents();
        table1.fixTable(jScrollPane1);
        
        chart.setTitle("Thống kê xử lý vi phạm");
        chart.addLegend("Đã xử lý vi phạm", Color.decode("#bbf7d0"), Color.decode("#4ade80"));
        chart.addLegend("Đang xử lý vi phạm", Color.decode("#f59e0b"), Color.decode("#fde68a"));
        
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
        
        
        setXuLyViPhamCbx();
        
        // Thêm lắng nghe sự kiện ItemListener
        xuLyViPhamCbx.addItemListener(xuLyViPhamItemListener);
        
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minus(Period.ofDays(30));
        setData(startTime, endTime);
        setDataForTable(startTime, endTime);
    }
    
    // Bộ lắng nghe sự kiện ItemListener cho nganhCbx
    private final ItemListener xuLyViPhamItemListener = (ItemEvent evt) -> {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            // Xử lý khi giá trị được chọn thay đổi
            xuLyViPhamCbxItemStateChanged(evt);
        }
    };
    
    private void xuLyViPhamCbxItemStateChanged(ItemEvent evt) {                                          
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            DateRangeBetween dateRangeBetween = dateRangeChooser.getSelectedDateBetween();

            DateRange dateRange = Helper.convertDateBetweenToLocalDateTime(dateRangeBetween);

            setData(dateRange.startTime, dateRange.endTime);
            setDataForTable(dateRange.startTime, dateRange.endTime);
        }
    }
    
    private List<Object[]> getData(LocalDateTime startTime, LocalDateTime endTime) {
        if (xuLyViPhamCbx.getSelectedItem().toString().equals("Đã xử lý")) {
            return xlBLL.thongKeXuLyDaXuLy(
                startTime,
                endTime
            );
        }
        
        return xlBLL.thongKeXuLyDangXuLy(
            startTime,
            endTime
        );
    }
    
    private List<Object[]> getDataForTable(LocalDateTime startTime, LocalDateTime endTime) {
        if (xuLyViPhamCbx.getSelectedItem().toString().equals("Đã xử lý")) {
            return xlBLL.thongKeXuLyDaXuLyForTable(
                startTime,
                endTime
            );
        }
        
        return xlBLL.thongKeXuLyDangXuLyForTable(
            startTime,
            endTime
        );
    }
    
    private void setDataForTable(LocalDateTime startTime, LocalDateTime endTime) {
        List<Object[]> data = getDataForTable(startTime, endTime);
        
        table1.removeAllRow();

        for (Object[] o : data) {
            table1.addRow(o);
        }
    }
    
    private void setData(LocalDateTime startTime, LocalDateTime endTime) {
        List<Object[]> data = getData(startTime, endTime);
        
        List<ModelChartOtherData> chartList = new ArrayList<>();
        
        for (Object[] o : data) {
            chartList.add(
                new ModelChartOtherData(
                    o[0].toString(), 
                    Integer.parseInt(o[1].toString()), 
                    Double.parseDouble(o[2].toString())
                )
            );
        }
        
        for (int i = 0; i < chartList.size(); i++) {
            ModelChartOtherData tv = chartList.get(i);
            chart.addData(new ModelCurveLineChart(tv.getLabel(), new double[] {tv.getCountTotal(), tv.getPriceTotal()}));
        }
        
        if (chartList.size() == 1) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedEndTime = endTime.format(formatter);
            chart.addData(new ModelCurveLineChart(formattedEndTime, new double[] {0}));
        }
        
        chart.start();
    }
    
    private void setXuLyViPhamCbx() {
        xuLyViPhamCbx.removeAllItems();
        xuLyViPhamCbx.addItem("Đã xử lý");
        xuLyViPhamCbx.addItem("Đang xử lý");
        
        xuLyViPhamCbx.setSelectedIndex(0);
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
        xuLyViPhamCbx = new com.example.project_2.components.combobox.ComboBoxSuggestion();
        chart = new com.example.project_2.components.charts.CurveLineChart();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.example.project_2.components.table.Table();

        setBackground(new java.awt.Color(255, 255, 255));
        setAutoscrolls(true);
        setPreferredSize(new java.awt.Dimension(1065, 726));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(159, 159, 159));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thống kê xử lý vi phạm");

        jLabel2.setText("Lọc theo ngày:");

        chart.setFillColor(true);

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Xử Lý", "Tên Thành Viên", "Hình Thức Xử Lý", "Số Tiền Xử Lý", "Ngày Xử Lý"
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chDate, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 505, Short.MAX_VALUE)
                        .addComponent(xuLyViPhamCbx, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(xuLyViPhamCbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.example.project_2.components.swing.TextField chDate;
    private com.example.project_2.components.charts.CurveLineChart chart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.example.project_2.components.table.Table table1;
    private com.example.project_2.components.combobox.ComboBoxSuggestion xuLyViPhamCbx;
    // End of variables declaration//GEN-END:variables
}
