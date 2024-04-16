/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.example.project_2.GUI;

import com.example.project_2.BLL.ThanhVienBLL;
import com.example.project_2.BLL.XuLyBLL;
import com.example.project_2.DAL.XuLyDAL;
import com.example.project_2.DTO.XuLy;
import com.example.project_2.DTO.ThanhVien;

import com.example.project_2.components.dialogs.ExcelDialog;
import com.example.project_2.components.dialogs.Message;
import com.example.project_2.components.dialogs.SuaXuLyDialog;
import com.example.project_2.components.dialogs.ThemXuLyDialog;
import com.example.project_2.components.table.EventAction;
import com.example.project_2.components.table.ModelAction;
import java.util.ArrayList;
import java.util.List;
import com.example.project_2.utils.Excel;
import java.sql.Date;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Hung
 */
public class XuLyViPhamGUI extends javax.swing.JPanel {

    private List<XuLy> xuly = new ArrayList<>();
    private XuLyBLL xulyBLL = new XuLyBLL();
    private ThanhVienBLL tvBLL = new ThanhVienBLL();

    public XuLyViPhamGUI() {
        initComponents();
        setOpaque(false);
        
        tableXuLyViPham.setActionColumn(7);
        tableXuLyViPham.fixTable(jScrollPane1);
        
        loadXuLy();
        
        addEventForSearchIcon();
        addEventForSearchTextField();
    }
    
    private void addEventForSearchTextField() {
        search.textField1.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    loadXuLy();
                }
            }
        });
    }
    private void addEventForSearchIcon() {
        search.button1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loadXuLy();
            }
        });
    }

    public void loadXuLy(){
        tableXuLyViPham.removeAllRow();
        
        String searchText = search.textField1.getText().trim();
        
        if (search.getPlaceholder().equalsIgnoreCase(searchText)) searchText = ""; 
        
        EventAction<XuLy> eventAction = new EventAction<XuLy>() {
            @Override
            public void update(XuLy xuly) {
                SuaXuLyDialog dialog = new SuaXuLyDialog(Main.getFrames()[0], true, xuly);
                dialog.showDialog();
                if (dialog.isOk()) {
                    loadXuLy();
                }
            }
            @Override
            public void delete(XuLy xuly) {
                
            }
        };
        xuly = xulyBLL.getAll();
        
        for(XuLy c : xuly)
        {   
            tableXuLyViPham.addRow(new Object[]{
                c.getMaXL(),
                c.getThanhVien().getMaTV(),
                c.getThanhVien().getHoTen(),
                c.getHinhThucXL(),
                c.getSoTien() != null? c.getSoTien():0,
                c.getNgayXL(),
                c.getTrangThaiXL() == 0? "Chưa xử lý":"Đã xử lý",
                new ModelAction<>(c, eventAction)});
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableXuLyViPham = new com.example.project_2.components.table.Table();
        btnNhapExcel = new com.example.project_2.components.swing.Button();
        btnThemViPham = new com.example.project_2.components.swing.Button();
        search = new com.example.project_2.components.swing.SearchWithIcon();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(159, 159, 159));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Xử lý vi phạm");

        tableXuLyViPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã xử lý", "Mã thành viên", "Tên thành viên", "Hình thức xử lý", "Tiền bồi thường", "Ngày xử lý", "Trạng thái", "Tùy chọn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableXuLyViPham);

        btnNhapExcel.setText("Nhập Excel");
        btnNhapExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNhapExcelMouseClicked(evt);
            }
        });

        btnThemViPham.setText("Thêm mới vi phạm");
        btnThemViPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemViPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemViPham, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNhapExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNhapExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThemViPham, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemViPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemViPhamActionPerformed
        ThemXuLyDialog dialog = new ThemXuLyDialog(Main.getFrames()[0], true);
        dialog.showDialog();        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemViPhamActionPerformed

    private void btnNhapExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhapExcelMouseClicked
        ExcelDialog dialog = new ExcelDialog(Main.getFrames()[0], true, List.of(
            List.of("Mã XL", Excel.Type.NUMERIC),
            List.of("Mã TV", Excel.Type.NUMERIC),
            List.of("Hình Thức Xử Lý", Excel.Type.STRING),
            List.of("Số Tiền", Excel.Type.NUMERIC),
            List.of("Ngày Xử Lý", Excel.Type.STRING),
            List.of("Trạng Thái Xử Lý", Excel.Type.STRING)

        ), row -> {
            int maXL = Integer.parseInt(row.get(0));
            long maTV = Long.parseLong(row.get(1));
            String hinhThucXuLy = StringUtils.capitalize(row.get(2));
            int soTien = Integer.parseInt(row.get(3));
            LocalDateTime ngayXuLy = LocalDateTime.parse(row.get(4));
            int trangThai = Integer.parseInt(row.get(5));
            
            ThanhVien tv = tvBLL.getById(maTV);

            if (tv != null && (trangThai == 1 || trangThai == 0)) {
                XuLy xl = new XuLy(maXL, tv, hinhThucXuLy, soTien, ngayXuLy, trangThai);
                return xulyBLL.add(xl);
            }
            
            return false;
        });

        dialog.setVisible(true);

        if (!dialog.isCancel()) {
            String title = "Thông báo";
            JOptionPane.showMessageDialog(this, "Nhập dữ liệu thành công.", title, JOptionPane.INFORMATION_MESSAGE);
            loadXuLy();
        }
    }//GEN-LAST:event_btnNhapExcelMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.example.project_2.components.swing.Button btnNhapExcel;
    private com.example.project_2.components.swing.Button btnThemViPham;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.example.project_2.components.swing.SearchWithIcon search;
    private com.example.project_2.components.table.Table tableXuLyViPham;
    // End of variables declaration//GEN-END:variables
}
