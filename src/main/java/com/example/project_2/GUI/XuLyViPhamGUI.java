/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.example.project_2.GUI;

import com.example.project_2.DAL.ThanhVienDAL;
import com.example.project_2.DAL.XuLyDAL;
import com.example.project_2.DTO.XuLy;
import com.example.project_2.components.dialogs.SuaXuLyDialog;
import com.example.project_2.components.dialogs.ThemXuLyDialog;
import com.example.project_2.components.table.EventAction;
import com.example.project_2.components.table.ModelAction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hung
 */
public class XuLyViPhamGUI extends javax.swing.JPanel {

    private List<XuLy> xuly = new ArrayList<>();
    private XuLyDAL xulyDal = new XuLyDAL();
    public XuLyViPhamGUI() {
        initComponents();
        setOpaque(false);
        loadXuLy();
    }

    public void loadXuLy(){
        tableXuLyViPham.removeAllRow();
        
        String searchText = search.getText().trim();
        
        if (searchText.equalsIgnoreCase(searchText)) searchText = ""; 
        EventAction<XuLy> eventAction = new EventAction<XuLy>() {
            @Override
            public void update(XuLy xuly) {
                SuaXuLyDialog dialog = new SuaXuLyDialog(Main.getFrames()[0], true, xuly);
                dialog.showDialog();
            }
            @Override
            public void delete(XuLy xuly) {
                
            }
        };
        xuly = xulyDal.getAll();
        
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
        jPanel1 = new javax.swing.JPanel();
        btnThemXuLy = new com.example.project_2.components.swing.Button();
        btnSearch = new com.example.project_2.components.swing.Button();
        search = new com.example.project_2.components.swing.TextField();

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
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableXuLyViPham);

        jPanel1.setBackground(new java.awt.Color(244, 244, 244));

        btnThemXuLy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemXuLy.setText("Thêm mới vi phạm");
        btnThemXuLy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemXuLyMouseClicked(evt);
            }
        });
        btnThemXuLy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemXuLyActionPerformed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemXuLy, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemXuLy, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE))
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemXuLyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemXuLyActionPerformed
        
    }//GEN-LAST:event_btnThemXuLyActionPerformed

    private void btnThemXuLyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemXuLyMouseClicked
        ThemXuLyDialog dialog = new ThemXuLyDialog(Main.getFrames()[0], true);
        dialog.showDialog();
    }//GEN-LAST:event_btnThemXuLyMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.example.project_2.components.swing.Button btnSearch;
    private javax.swing.JButton btnThemXuLy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.example.project_2.components.swing.TextField search;
    private com.example.project_2.components.table.Table tableXuLyViPham;
    // End of variables declaration//GEN-END:variables
}
