package com.example.project_2.GUI;

import com.example.project_2.BLL.ThietBiBLL;
import com.example.project_2.DTO.ThietBi;
import com.example.project_2.components.dialogs.ExcelDialog;
import com.example.project_2.components.dialogs.Message;
import com.example.project_2.components.dialogs.SuaThietBiDialog;
import com.example.project_2.components.dialogs.ThemThietBiDialog;
import com.example.project_2.components.dialogs.XoaNhieuThietBiDialog;
import com.example.project_2.components.table.EventAction;
import com.example.project_2.components.table.ModelAction;
import com.example.project_2.utils.Excel;
import com.example.project_2.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Hung
 */
public class ThietBiGUI extends javax.swing.JPanel {

    /**
     * Creates new form ThietBiGUI
     */
    private List<ThietBi> thietbi = new ArrayList<>();
    private ThietBiBLL thietBiBLL = new ThietBiBLL();
    
    public ThietBiGUI() {
        initComponents();
        tableThietBi.setActionColumn(3);
        tableThietBi.fixTable(jScrollPane1);
        setOpaque(false);
        loadThietBi();
        
        addEventForSearchIcon();
        addEventForSearchTextField();
    }
    
    private void addEventForSearchTextField() {
        search.textField1.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    loadThietBi();
                }
            }
        });
    }
    private void addEventForSearchIcon() {
        search.button1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loadThietBi();
            }
        });
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboSuggestionUI1 = new com.example.project_2.components.combobox.ComboSuggestionUI();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableThietBi = new com.example.project_2.components.table.Table();
        jLabel2 = new javax.swing.JLabel();
        btnXoaDieuKien = new com.example.project_2.components.swing.Button();
        btnNhapExcel = new com.example.project_2.components.swing.Button();
        btnThem = new com.example.project_2.components.swing.Button();
        search = new com.example.project_2.components.swing.SearchWithIcon();

        setPreferredSize(new java.awt.Dimension(1064, 726));

        tableThietBi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Thiết Bị", "Tên Thiết Bị", "Mô tả", "Tùy chọn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableThietBi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableThietBiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableThietBi);

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(159, 159, 159));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Quản lý thiết bị");

        btnXoaDieuKien.setText("Xóa thiết bị theo điều kiện");
        btnXoaDieuKien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaDieuKienMouseClicked(evt);
            }
        });
        btnXoaDieuKien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDieuKienActionPerformed(evt);
            }
        });

        btnNhapExcel.setText("Nhập Excel");
        btnNhapExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNhapExcelMouseClicked(evt);
            }
        });

        btnThem.setText("Thêm thiết bị");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoaDieuKien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNhapExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 982, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1064, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(98, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnXoaDieuKien, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNhapExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(jLabel2)
                    .addContainerGap(647, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaDieuKienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDieuKienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaDieuKienActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        ThemThietBiDialog dialog = new ThemThietBiDialog(Main.getFrames()[0], true);
        dialog.showDialog();
    }//GEN-LAST:event_btnThemActionPerformed

    private void tableThietBiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableThietBiMouseClicked
        if (evt.getClickCount() == 2){
            int selectedRound = tableThietBi.getSelectedRow();
            
            if (selectedRound != -1){
                int maThietBi = Integer.parseInt(tableThietBi.getValueAt(selectedRound, 0).toString());
                
                SuaThietBiDialog dialog = new SuaThietBiDialog(Main.getFrames()[0], true, maThietBi);
                dialog.showDialog();
                loadThietBi();
            }else{
                Message mess = new Message(Main.getFrames()[0], true);
                mess.showMessage("Vui lòng chọn dòng");
            }
        }
    }//GEN-LAST:event_tableThietBiMouseClicked

    private void btnXoaDieuKienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaDieuKienMouseClicked
        XoaNhieuThietBiDialog dialog = new XoaNhieuThietBiDialog(Main.getFrames()[0], true);
        dialog.showDialog();
        if (dialog.isOk()) {
            loadThietBi();
        }
    }//GEN-LAST:event_btnXoaDieuKienMouseClicked

    private void btnNhapExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhapExcelMouseClicked
        ExcelDialog dialog = new ExcelDialog(Main.getFrames()[0], true, List.of(
            List.of("Mã TB", Excel.Type.NUMERIC),
            List.of("Tên TB", Excel.Type.STRING),
            List.of("Mô tả TB", Excel.Type.STRING)
        ), row -> {
            int maTB = Integer.parseInt(row.get(0));
            String tenTB = StringUtils.capitalize(row.get(1));
            String moTa = row.get(2);
            ThietBi tb = new ThietBi(maTB, tenTB, moTa);
            return thietBiBLL.add(tb);
        });
        
        dialog.setVisible(true);
        
        if (!dialog.isCancel()) {
            String title = "Thông báo";
            JOptionPane.showMessageDialog(this, "Nhập dữ liệu thành công.", title, JOptionPane.INFORMATION_MESSAGE);
            loadThietBi();
        }
    }//GEN-LAST:event_btnNhapExcelMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.example.project_2.components.swing.Button btnNhapExcel;
    private com.example.project_2.components.swing.Button btnThem;
    private com.example.project_2.components.swing.Button btnXoaDieuKien;
    private com.example.project_2.components.combobox.ComboSuggestionUI comboSuggestionUI1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.example.project_2.components.swing.SearchWithIcon search;
    private com.example.project_2.components.table.Table tableThietBi;
    // End of variables declaration//GEN-END:variables

    private void loadThietBi() {
        tableThietBi.removeAllRow();
        
        String searchText = search.textField1.getText().trim();
        
        if (search.getPlaceholder().equalsIgnoreCase(searchText)) searchText = "";
        
        Map<String, Object> searchCriteria = new HashMap<>();
        
        if (!searchText.isEmpty()) {
            searchCriteria.put("TenTB", searchText);
        }

        thietbi = thietBiBLL.search(searchCriteria, ThietBi.class);
        
        EventAction<ThietBi> eventAction = new EventAction<ThietBi>() {
            @Override
            public void update(ThietBi thietbi) {
                SuaThietBiDialog dialog = new SuaThietBiDialog(Main.getFrames()[0], true, thietbi.getMaTB());
                dialog.showDialog();
                Main.recreateGUI(new ThietBiGUI());
            }
            @Override
            public void delete(ThietBi thietbi) {
                Message mess = new Message(Main.getFrames()[0], true);
                mess.showMessage("Bạn muốn xóa thiết bị ???");
                if (mess.isOk()){
                    if (thietBiBLL.delete(thietbi)) {
                        mess = new Message(Main.getFrames()[0], true);
                        mess.showMessage("Xóa thiết bị thành công");
                        Main.recreateGUI(new ThietBiGUI());
                    } else {
                        mess = new Message(Main.getFrames()[0], true);
                        mess.showMessage("Xóa thiết bị thất bại");
                    }
                }
            }
        };
        
        for(ThietBi c : thietbi)
        {   
            tableThietBi.addRow(new Object[]{
                c.getMaTB(),
                c.getTenTB(),
                c.getMoTaTB(),
                new ModelAction<>(c, eventAction)});
        } 
    }
    
}
