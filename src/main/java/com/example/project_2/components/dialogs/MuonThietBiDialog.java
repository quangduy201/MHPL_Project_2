/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.example.project_2.components.dialogs;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.example.project_2.BLL.ThanhVienBLL;
import com.example.project_2.BLL.ThongTinSDBLL;
import com.example.project_2.BLL.XuLyBLL;
import com.example.project_2.DTO.ThanhVien;
import com.example.project_2.DTO.ThongTinSD;
import com.example.project_2.DTO.XuLy;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import com.example.project_2.BLL.ThietBiBLL;
import com.example.project_2.DTO.ThietBi;
import com.example.project_2.GUI.Main;

/**
 *
 * @author Admin
 */
public class MuonThietBiDialog extends javax.swing.JDialog {
    private ThanhVienBLL thanhVienBLL = new ThanhVienBLL();
    private ThietBiBLL thietbiBLL = new ThietBiBLL();
    private ThongTinSDBLL thongTinSDBLL = new ThongTinSDBLL();
    private XuLyBLL xuLyBLL = new XuLyBLL();

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    private boolean ok;
    private final Animator animator;
    private boolean show = true;
    
    public MuonThietBiDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
//        final ListCellRenderer thanhVienRenderer = cbbThanhVien.getRenderer();
//        final ListCellRenderer thietBiRenderer = cbbThietBi.getRenderer();
//        cbbThanhVien.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
//            if (value instanceof ThanhVien) {
//                value = ((ThanhVien) value).getMaTV();
//            }
//            return thanhVienRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
//        });
//        cbbThietBi.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
//            if (value instanceof ThietBi) {
//                value = ((ThietBi) value).getMaTB();
//            }
//            return thietBiRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
//        });

        loadData();
            
        setOpacity(0f);
        getContentPane().setBackground(Color.WHITE);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (show) {
                    setOpacity(fraction);
                } else {
                    setOpacity(1f - fraction);
                }
            }

            @Override
            public void end() {
                if (show == false) {
                    setVisible(false);
                }
            }

        };
        animator = new Animator(200, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        
        setLocationRelativeTo(null);
        
    }

    public void loadData() {
        List<ThanhVien> thanhVienList = thanhVienBLL.getAll();
        List<ThietBi> thietBiList = thietbiBLL.getAll();
        setCbbThanhVien(thanhVienList);
        setCbbThietBi(thietBiList);
    }

    public void setCbbThanhVien(List<ThanhVien> thanhVienList) {
        cbbThanhVien.removeAllItems();
        for (ThanhVien thanhVien : thanhVienList) {
            cbbThanhVien.addItem(thanhVien);
        }
    }

    public void setCbbThietBi(List<ThietBi> thietBiList) {
        cbbThietBi.removeAllItems();
        for (ThietBi thietBi : thietBiList) {
            cbbThietBi.addItem(thietBi);
        }
    }

    public void showDialog() {
        animator.start();
        setVisible(true);
    }
    
    private void closeMenu() {
        if (animator.isRunning()) {
            animator.stop();
        }
        show = false;
        animator.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup4 = new javax.swing.ButtonGroup();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnHuy = new com.example.project_2.components.swing.Button();
        btnConfirm = new com.example.project_2.components.swing.Button();
        cbbThanhVien = new com.example.project_2.components.combobox.ComboBoxSuggestion();
        jLabel7 = new javax.swing.JLabel();
        cbbThietBi = new com.example.project_2.components.combobox.ComboBoxSuggestion();

        setUndecorated(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 34)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(159, 159, 159));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Mượn thiết bị");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Chọn thành viên:");

        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnConfirm.setBackground(new java.awt.Color(51, 102, 255));
        btnConfirm.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirm.setText("Xác nhận");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Chọn thiết bị:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbThietBi, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbThanhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbThanhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbThietBi, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 290));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
       
    }//GEN-LAST:event_btCancelActionPerformed

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
    
    }//GEN-LAST:event_btAddActionPerformed
  
    private void btResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResetActionPerformed
    
    }//GEN-LAST:event_btResetActionPerformed

    private void txtTimeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimeKeyTyped

    }//GEN-LAST:event_txtTimeKeyTyped

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        // Check nếu thành viên đang bị vi phạm
        ThanhVien thanhVien = (ThanhVien) cbbThanhVien.getSelectedItem();
        Map<String, Object> criteria = new HashMap<>();
        assert thanhVien != null;
        criteria.put("thanhVien.MaTV", thanhVien.getMaTV());
        criteria.put("TrangThaiXL", 0);
        List<XuLy> xuLyList = xuLyBLL.getByCriteria(criteria);
        if (!xuLyList.isEmpty()) {
            Message message = new Message(Main.getFrames()[0], true);
            message.showMessage("Thành viên đang bị vi phạm: " + xuLyList.get(0).getHinhThucXL());
            return;
        }

        // Check nếu thiết bị đang được mượn
        ThietBi thietBi = (ThietBi) cbbThietBi.getSelectedItem();
        criteria = new HashMap<>();
        assert thietBi != null;
        criteria.put("thietBi.MaTB", thietBi.getMaTB());
        criteria.put("TGTra", null);
        List<ThongTinSD> thongTinSDList = thongTinSDBLL.getByCriteria(criteria);
        if (!thongTinSDList.isEmpty()) {
            Message message = new Message(Main.getFrames()[0], true);
            message.showMessage("Thiết bị đã được mượn.");
            return;
        }

        ThongTinSD thongTinSD = new ThongTinSD(0, thanhVien, thietBi, null, LocalDateTime.now(), null, null);
        if (thongTinSDBLL.add(thongTinSD)) {
            closeMenu();
            ok = true;
            Message message = new Message(Main.getFrames()[0], true);
            message.showMessage("Mượn thiết bị thành công.");
        } else {
            Message message = new Message(Main.getFrames()[0], true);
            message.showMessage("Mượn thiết bị thất bại.");
        }
    }//GEN-LAST:event_btnConfirmActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.example.project_2.components.swing.Button btnConfirm;
    private com.example.project_2.components.swing.Button btnHuy;
    private javax.swing.ButtonGroup buttonGroup4;
    private com.example.project_2.components.combobox.ComboBoxSuggestion<ThanhVien> cbbThanhVien;
    private com.example.project_2.components.combobox.ComboBoxSuggestion<ThietBi> cbbThietBi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables

}
