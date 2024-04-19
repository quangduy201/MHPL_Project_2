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
public class SuaThongTinTVDialog extends javax.swing.JDialog {
    private final ThanhVienBLL thanhVienBLL;
    private final ThanhVien thanhVien;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    private boolean ok;
    private final Animator animator;
    private boolean show = true;
    
    public SuaThongTinTVDialog(java.awt.Frame parent, boolean modal, long maTV) {
        super(parent, modal);
        initComponents();

        thanhVienBLL = new ThanhVienBLL();
        thanhVien = thanhVienBLL.getById(maTV);
        loadThongTinTV();

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

    private void loadThongTinTV() {
        txtMaTV.setText(thanhVien.getMaTV().toString());
        txtHoTen.setText(thanhVien.getHoTen());
        txtSdt.setText(thanhVien.getSDT());
        txtKhoa.setText(thanhVien.getKhoa());
        txtNganh.setText(thanhVien.getNganh());
        txtEmail.setText(thanhVien.getEmail());
    }

    private ThanhVien getThongTinTV() {
        return new ThanhVien(
                thanhVien.getMaTV(),
                StringUtils.capitalizeFully(txtHoTen.getText().trim()),
                txtEmail.getText().trim(),
                thanhVien.getPassword(),
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
                txtEmail.getText().trim()
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
        btnCancel = new com.example.project_2.components.swing.Button();
        txtNganh = new com.example.project_2.components.swing.TextField();
        jLabel5 = new javax.swing.JLabel();
        txtHoTen = new com.example.project_2.components.swing.TextField();
        btnEdit = new com.example.project_2.components.swing.Button();
        jLabel10 = new javax.swing.JLabel();
        txtEmail = new com.example.project_2.components.swing.TextField();
        jLabel11 = new javax.swing.JLabel();

        setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(txtSdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 210, 47));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Khoa:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Số điện thoại:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        txtMaTV.setEditable(false);
        txtMaTV.setEditable(false);
        txtMaTV.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.add(txtMaTV, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 210, 47));
        jPanel3.add(txtKhoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 210, 47));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã thành viên:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(159, 159, 159));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Sửa thông tin thành viên");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 7, 427, -1));

        btnCancel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCancel.setText("Hủy");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel3.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 490, 120, 40));
        jPanel3.add(txtNganh, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 210, 47));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Họ tên:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));
        jPanel3.add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 210, 47));

        btnEdit.setBackground(new java.awt.Color(51, 102, 255));
        btnEdit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Sửa");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel3.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 490, 120, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Email:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, -1, -1));
        jPanel3.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, 210, 47));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Ngành:");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        closeMenu();
        ok = false;
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (!validateThongTinTV()) {
            return;
        }

        ThanhVien thanhVien = getThongTinTV();
        Map<String, Object> criteria = Map.of(
                "HoTen", thanhVien.getHoTen(),
                "Khoa", thanhVien.getKhoa(),
                "Nganh", thanhVien.getNganh(),
                "SDT", thanhVien.getSDT(),
                "Email", thanhVien.getEmail()
        );
        List<ThanhVien> thanhVienList = thanhVienBLL.getByCriteria(criteria);
        if (!thanhVienList.isEmpty()) {
            Message message = new Message(Main.getFrames()[0], true);
            message.showMessage("Thông tin thành viên đã tồn tại.");
            return;
        }
        if (thanhVienBLL.update(thanhVien)) {
            closeMenu();
            ok = true;
            Message message = new Message(Main.getFrames()[0], true);
            message.showMessage("Cập nhật thành viên thành công.");
        } else {
            Message message = new Message(Main.getFrames()[0], true);
            message.showMessage("Đã xảy ra lỗi khi cập nhật thành viên.");
        }
    }//GEN-LAST:event_btnEditActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEdit;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtKhoa;
    private javax.swing.JTextField txtMaTV;
    private javax.swing.JTextField txtNganh;
    private javax.swing.JTextField txtSdt;
    // End of variables declaration//GEN-END:variables

}
