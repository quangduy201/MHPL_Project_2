/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.example.project_2.components.dialogs;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import com.example.project_2.BLL.ThanhVienBLL;
import com.example.project_2.DTO.ThanhVien;
import com.example.project_2.GUI.Main;
import com.example.project_2.utils.StringUtils;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author Admin
 */
public class ThemThongTinTVDialog extends javax.swing.JDialog {
    private final ThanhVienBLL thanhVienBLL;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    private boolean ok;
    private final Animator animator;
    private boolean show = true;
    
    public ThemThongTinTVDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        thanhVienBLL = new ThanhVienBLL();

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

    private ThanhVien getThongTinTV() {
        return new ThanhVien(
                Long.parseLong(txtMaTV.getText().trim()),
                StringUtils.capitalizeFully(txtHoTen.getText().trim()),
                // TODO: add the email
                "abc@example.com", // TODO: txtEmail.getText().trim()
                "", // Reset password
                txtKhoa.getText().trim().toUpperCase(),
                txtNganh.getText().trim().toUpperCase(),
                txtSdt.getText().trim()
        );
    }

    private boolean validateThongTinTV() {
        String error = thanhVienBLL.validateThanhVien(
                txtMaTV.getText().trim(),
                StringUtils.capitalizeFully(txtHoTen.getText().trim()),
                txtKhoa.getText().trim().toUpperCase(),
                txtNganh.getText().trim().toUpperCase(),
                txtSdt.getText().trim(),
                "abc@example.com" // TODO: txtEmail.getText().trim()
        );
        if (error != null) {
            Message message = new Message(Main.getFrames()[0], true);
            message.showMessage(error);
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup4 = new javax.swing.ButtonGroup();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel3 = new javax.swing.JPanel();
        txtSdt = new com.example.project_2.components.swing.TextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMaTV = new com.example.project_2.components.swing.TextField();
        txtKhoa = new com.example.project_2.components.swing.TextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnRefresh = new com.example.project_2.components.swing.Button();
        txtNganh = new com.example.project_2.components.swing.TextField();
        jLabel5 = new javax.swing.JLabel();
        txtHoTen = new com.example.project_2.components.swing.TextField();
        jLabel10 = new javax.swing.JLabel();
        btnAdd = new com.example.project_2.components.swing.Button();
        btnCancel = new com.example.project_2.components.swing.Button();

        setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(txtSdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 230, 47));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Khoa:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Số điện thoại:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        txtMaTV.setEditable(true);
        txtMaTV.setEditable(true);
        jPanel3.add(txtMaTV, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 230, 47));
        jPanel3.add(txtKhoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 230, 47));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã thành viên:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(159, 159, 159));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Thêm thành viên");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 7, 460, -1));

        btnRefresh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnRefresh.setText("Làm mới");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jPanel3.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 120, 40));
        jPanel3.add(txtNganh, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 230, 47));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Họ tên:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));
        jPanel3.add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 230, 47));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Ngành:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        btnAdd.setBackground(new java.awt.Color(51, 102, 255));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel3.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 420, 120, 40));

        btnCancel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCancel.setText("Hủy");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel3.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 120, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        txtMaTV.setText("");
        txtHoTen.setText("");
        txtSdt.setText("");
        txtKhoa.setText("");
        txtNganh.setText("");
        // TODO: add the email
//        txtEmail.setText("");
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        closeMenu();
        ok = false;
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (!validateThongTinTV()) {
            return;
        }

        ThanhVien thanhVien = getThongTinTV();
        if (thanhVienBLL.getById(thanhVien.getMaTV()) != null) {
            Message message = new Message(Main.getFrames()[0], true);
            message.showMessage("Mã thành viên đã tồn tại.");
            return;
        }
        Map<String, Object> criteria = Map.of(
                "HoTen", thanhVien.getHoTen(),
                "Khoa", thanhVien.getKhoa(),
                "Nganh", thanhVien.getNganh(),
                "SDT", thanhVien.getSDT(),
                "Email", thanhVien.getEmail()
        );
        List<ThanhVien> thanhVienList = thanhVienBLL.getByCriteria(criteria);
        if (!thanhVienList.isEmpty()) {
            // TODO: ask the user if they would like to continue?
            // TODO: there will be a duplication of the Thanh Vien if they continue.
            System.out.println("Thông tin thành viên đã tồn tại. Bạn có muốn tiếp tục?");
            return;
        }
        if (thanhVienBLL.add(thanhVien)) {
            closeMenu();
            ok = true;
            Message message = new Message(Main.getFrames()[0], true);
            message.showMessage("Thêm thành viên mới thành công.");
        } else {
            Message message = new Message(Main.getFrames()[0], true);
            message.showMessage("Đã xảy ra lỗi khi thêm mới thành viên.");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnRefresh;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtKhoa;
    private javax.swing.JTextField txtMaTV;
    private javax.swing.JTextField txtNganh;
    private javax.swing.JTextField txtSdt;
    // End of variables declaration//GEN-END:variables

}
