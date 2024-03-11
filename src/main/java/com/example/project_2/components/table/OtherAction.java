package com.example.project_2.components.table;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OtherAction extends javax.swing.JPanel {

    public OtherAction(ModelAction data, boolean isOnlyUpdate) {
        initComponents();
        
        if (isOnlyUpdate) {
            cmdDelete.hide();
            cmdEdit.show();
        } else {
            cmdEdit.hide();
            cmdDelete.show();
        }
        
        cmdEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                data.getEvent().update(data.getObject());
            }
        });
        cmdDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                data.getEvent().delete(data.getObject());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.setColor(new Color(230, 230, 230));
        grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdEdit = new com.example.project_2.components.table.ButtonTable();
        cmdDelete = new com.example.project_2.components.table.ButtonTable();

        cmdEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        add(cmdEdit);

        cmdDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        add(cmdDelete);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.example.project_2.components.table.ButtonTable cmdDelete;
    private com.example.project_2.components.table.ButtonTable cmdEdit;
    // End of variables declaration//GEN-END:variables
}
