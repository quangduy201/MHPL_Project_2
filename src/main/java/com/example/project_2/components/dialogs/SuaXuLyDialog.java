/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.example.project_2.components.dialogs;

import com.example.project_2.BLL.ThanhVienBLL;
import com.example.project_2.BLL.XuLyBLL;
import com.example.project_2.DTO.ThanhVien;
import com.example.project_2.DTO.XuLy;
import com.example.project_2.GUI.Main;
import com.example.project_2.components.date_chooser.DateChooser;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @author Admin
 */
public class SuaXuLyDialog extends javax.swing.JDialog {
    private int DEFALUT_WIDTH;
    private DefaultTableModel model;
    private DateChooser dateChooser = new DateChooser();
    private ThanhVienBLL thanhVienBLL = new ThanhVienBLL();
    private XuLyBLL xulyBLL = new XuLyBLL();

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    private boolean ok;
    private final Animator animator;
    private boolean show = true;

    public SuaXuLyDialog(java.awt.Frame parent, boolean modal, XuLy xuly) {
        super(parent, modal);
        initComponents();

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
        setCbxHinhThuc();
        setDataForm(xuly);
        cbbHTXL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbHTXLActionPerformed(evt, xuly);
            }
        });

    }

    private void cbbHTXLActionPerformed(java.awt.event.ActionEvent evt, XuLy xuly) {
        String selectedOption = (String) cbbHTXL.getSelectedItem();
        if (selectedOption != null) {
            switch (selectedOption) {
                case "Khóa thẻ 1 tháng":
                case "Khóa thẻ 2 tháng":
                case "Khóa thẻ 3 tháng":
                    tfTienBoiThuong.setEnabled(false);
                    tfTienBoiThuong.setBackground(new Color(244, 244, 244));
                    if (xuly.getSoTien() == 0) {
                        tfTienBoiThuong.setText("0");
                    }
                    break;
                case "Bồi thường mất tài sản":
                case "Khóa thẻ 1 tháng và bồi thường":
                    tfTienBoiThuong.setEnabled(true);
                    tfTienBoiThuong.setBackground(Color.WHITE);
                    break;
                default:
                    tfTienBoiThuong.setEnabled(false);
                    break;
            }
        }
    }

    public void suaXuLy() {
        int maXuLy = Integer.parseInt(tfXuLy.getText());
        long maThanhVien = Long.parseLong(getMaThanhVienFromSelectedItem());
        String htxl = (String) cbbHTXL.getSelectedItem();
        int sotien = 0;
        if (cbbHTXL.getSelectedIndex() == 3 || cbbHTXL.getSelectedIndex() == 4) {
            sotien = Integer.parseInt(tfTienBoiThuong.getText());
        }

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(chDate.getText(), inputFormatter);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(outputFormatter);

        LocalTime defaultTime = LocalTime.of(Integer.parseInt(tfGio.getText()), Integer.parseInt(tfPhut.getText()));
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.parse(formattedDate), defaultTime);

        int trangthai = toggleTrangThai.isSelected() ? 1 : 0;

        XuLy xuLy = new XuLy(maXuLy, thanhVienBLL.getById(maThanhVien), htxl, sotien, dateTime, trangthai);
        if (xulyBLL.update(xuLy)) {
            Message mess = new Message(Main.getFrames()[0], true);
            mess.showMessage("Sửa xử lý vi phạm thành công");
            dispose();
        } else {
            Message mess = new Message(Main.getFrames()[0], true);
            mess.showMessage("Sửa xử lý vi phạm thất bại");
        }
    }

    private String getMaThanhVienFromSelectedItem() {
        Object selectedItem = cbbMaTV.getSelectedItem();

        if (selectedItem instanceof String) {
            String selectedString = (String) selectedItem;

            int indexOfDash = selectedString.indexOf('-');
            if (indexOfDash != -1) {
                String maThanhVien = selectedString.substring(0, indexOfDash).trim();
                return maThanhVien;
            }
        }
        return null;
    }

    private void setCbxHinhThuc() {
        System.out.println(chDate.getText());
        cbbHTXL.removeAllItems();
        List<String> htList = List.of(
                "Khóa thẻ 1 tháng",
                "Khóa thẻ 2 tháng",
                "Khóa thẻ 3 tháng",
                "Bồi thường mất tài sản",
                "Khóa thẻ 1 tháng và bồi thường"
        );

        for (String htxl : htList) {
            cbbHTXL.addItem(htxl);
        }
    }

    public void setDataForm(XuLy xuly) {
        tfXuLy.setText(String.valueOf(xuly.getMaXL()));
        tfXuLy.setEnabled(false);
        cbbMaTV.removeAllItems();
        List<ThanhVien> tvList = thanhVienBLL.getAll();

        for (ThanhVien tv : tvList) {
            cbbMaTV.addItem(tv.getMaTV() + " - " + tv.getHoTen());
        }

        int maXuLy = xuly.getMaXL();

        for (int i = 0; i < cbbMaTV.getItemCount(); i++) {
            String item = (String) cbbMaTV.getItemAt(i);
            int index = item.indexOf('-');
            int maThanhVien = Integer.parseInt(item.substring(0, index).trim());
            if (maThanhVien == maXuLy) {
                cbbMaTV.setSelectedIndex(i);
                break;
            }
        }

        String htxl = xuly.getHinhThucXL();
        for (int i = 0; i < cbbHTXL.getItemCount(); i++) {
            String item = (String) cbbHTXL.getItemAt(i);
            if (htxl.equalsIgnoreCase(item)) {
                cbbHTXL.setSelectedIndex(i);
                break;
            }
        }

        if (cbbHTXL.getSelectedIndex() < 3) tfTienBoiThuong.setEnabled(false);
        LocalDateTime datetime = xuly.getNgayXL();
        LocalDate ngay = datetime.toLocalDate();
        System.out.println(ngay);
        int gio = datetime.getHour();
        int phut = datetime.getMinute();
        String gioTime = String.valueOf(gio);
        String phutTime = String.valueOf(phut);

        String gioTimeString = gioTime.length() == 1 ? "0" + gioTime : gioTime;
        String phutTimeString = phutTime.length() == 1 ? "0" + phutTime : phutTime;

        tfGio.setText(gioTimeString);
        tfPhut.setText(phutTimeString);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = ngay.format(formatter);
        LocalDate localDate = LocalDate.parse(formattedDate, formatter);
        Date date = java.sql.Date.valueOf(localDate);
//        chDate.setText(formattedDate);

        Integer sotien = 0;
        if (xuly.getSoTien() != null) {
            sotien = xuly.getSoTien();
            tfTienBoiThuong.setText(sotien.toString());

        } else {
            tfTienBoiThuong.setText("0");
        }
        boolean trangthai = (xuly.getTrangThaiXL() == 1) ? true : false;
        toggleTrangThai.setSelected(trangthai);
        dateChooser.setTextReference(chDate);
        dateChooser.setSelectedDate(date);
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

    private boolean checkValid() {
        if (tfXuLy.getText().isBlank()) {
            Message mess = new Message(Main.getFrames()[0], true);
            mess.showMessage("Không được để trống mã xử lý");
            return false;
        }
        if (!tfXuLy.getText().matches("\\d+")) {
            Message mess = new Message(Main.getFrames()[0], true);
            mess.showMessage("Vui lòng nhập số cho mã kí tự");
            return false;
        }
        if (xulyBLL.getById(Integer.parseInt(tfXuLy.getText())) != null) {
            Message mess = new Message(Main.getFrames()[0], true);
            mess.showMessage("Mã xử lý đã tồn tại");
            return false;
        }
        if (tfGio.getText().isBlank() || tfPhut.getText().isBlank()) {
            Message mess = new Message(Main.getFrames()[0], true);
            mess.showMessage("Không được để trống thời gian");
            return false;
        }
        if (tfGio.getText().isBlank() || tfPhut.getText().isBlank()) {
            Message mess = new Message(Main.getFrames()[0], true);
            mess.showMessage("Không được để trống thời gian");
            return false;
        }
        if (!tfTienBoiThuong.isEnabled()) {
            if (tfTienBoiThuong.getText().isBlank()) {
                // Nếu trống, hiển thị thông báo và trả về false
                Message mess = new Message(Main.getFrames()[0], true);
                mess.showMessage("Không được để trống tiền bồi thường");
                return false;
            }
            if (!tfTienBoiThuong.getText().matches("\\d+")) {
                Message mess = new Message(Main.getFrames()[0], true);
                mess.showMessage("Vui lòng nhập số tiền bồi thường");
                return false;
            }
        }

        if (!tfGio.getText().matches("\\d+") || !tfPhut.getText().matches("\\d+")) {
            Message mess = new Message(Main.getFrames()[0], true);
            mess.showMessage("Vui lòng nhập số cho giờ và phút");
            return false;
        }

        int gio = Integer.parseInt(tfGio.getText());
        int phut = Integer.parseInt(tfPhut.getText());

        if (gio < 0 || gio > 23 || phut < 0 || phut > 59) {
            Message mess = new Message(Main.getFrames()[0], true);
            mess.showMessage("Thời gian không hợp lệ");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup4 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        tfXuLy = new com.example.project_2.components.swing.TextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfTienBoiThuong = new com.example.project_2.components.swing.TextField();
        chDate = new com.example.project_2.components.swing.TextField();
        toggleTrangThai = new com.example.project_2.components.swing.ToggleButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbbMaTV = new com.example.project_2.components.combobox.ComboBoxSuggestion();
        cbbHTXL = new com.example.project_2.components.combobox.ComboBoxSuggestion();
        btnSua = new com.example.project_2.components.swing.Button();
        btnHuy = new com.example.project_2.components.swing.Button();
        tfGio = new com.example.project_2.components.swing.TextField();
        jLabel15 = new javax.swing.JLabel();
        tfPhut = new com.example.project_2.components.swing.TextField();
        jLabel16 = new javax.swing.JLabel();

        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));

        tfXuLy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfXuLyActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Mã xử lý");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Mã thành viên");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Hình thức xử lý");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Tiền bồi thường");

        tfTienBoiThuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTienBoiThuongActionPerformed(evt);
            }
        });

        chDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chDateMouseClicked(evt);
            }
        });
        chDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chDateActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Ngày xử lý");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Trạng thái");

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(159, 159, 159));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cập nhật vi phạm");

        btnSua.setBackground(new java.awt.Color(51, 102, 255));
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        tfGio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfGioActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Thời gian:");

        tfPhut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPhutActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel16.setText(":");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(cbbHTXL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbbMaTV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tfTienBoiThuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tfXuLy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(toggleTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel13)
                                                                        .addComponent(chDate, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(23, 23, 23)
                                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel15)
                                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                                .addGap(11, 11, 11)
                                                                                .addComponent(tfGio, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(0, 0, 0)
                                                                                .addComponent(jLabel16)
                                                                                .addGap(0, 0, 0)
                                                                                .addComponent(tfPhut, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                                .addGap(88, 88, 88))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(101, 101, 101)
                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(17, 17, 17)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(tfXuLy, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(cbbMaTV, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbHTXL, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(tfTienBoiThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(13, 13, 13)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(chDate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(51, 51, 51))))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(tfGio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(tfPhut, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(51, 51, 51)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(toggleTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(83, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
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

    private void chDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chDateActionPerformed
        dateChooser.showPopup();
    }//GEN-LAST:event_chDateActionPerformed

    private void chDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chDateMouseClicked
        dateChooser.showPopup();
    }//GEN-LAST:event_chDateMouseClicked

    private void tfTienBoiThuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTienBoiThuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTienBoiThuongActionPerformed

    private void tfXuLyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfXuLyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfXuLyActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        suaXuLy();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void tfGioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfGioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfGioActionPerformed

    private void tfPhutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPhutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPhutActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.example.project_2.components.swing.Button btnHuy;
    private com.example.project_2.components.swing.Button btnSua;
    private javax.swing.ButtonGroup buttonGroup4;
    private com.example.project_2.components.combobox.ComboBoxSuggestion cbbHTXL;
    private com.example.project_2.components.combobox.ComboBoxSuggestion cbbMaTV;
    private com.example.project_2.components.swing.TextField chDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private com.example.project_2.components.swing.TextField tfGio;
    private com.example.project_2.components.swing.TextField tfPhut;
    private com.example.project_2.components.swing.TextField tfTienBoiThuong;
    private com.example.project_2.components.swing.TextField tfXuLy;
    private com.example.project_2.components.swing.ToggleButton toggleTrangThai;
    // End of variables declaration//GEN-END:variables

}
