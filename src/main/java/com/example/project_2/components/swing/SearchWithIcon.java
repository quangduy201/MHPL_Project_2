package com.example.project_2.components.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class SearchWithIcon extends javax.swing.JPanel {
    private String placeholder = "Search...";

    public SearchWithIcon(String placeholder) {
        initComponents();
        setOpaque(false);
        addPlaceholderStyle(textField1);
        this.placeholder = placeholder;
    }
    
    public SearchWithIcon() {
        initComponents();
        setOpaque(false);
        addPlaceholderStyle(textField1);
    }
    
    public String getPlaceholder() {
        return this.placeholder;
    }
    
    public void addPlaceholderStyle(TextField t) {
        Font font = t.getFont();
        font = font.deriveFont(Font.ITALIC);
        t.setFont(font);
        t.setForeground(Color.GRAY);
    }
    
    public void removePlaceholderStyle(TextField t) {
        Font font = t.getFont();
        font = font.deriveFont(Font.PLAIN);
        t.setFont(font);
        t.setForeground(Color.BLACK);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textField1 = new com.example.project_2.components.swing.TextField();
        button1 = new com.example.project_2.components.swing.Button();

        setBackground(new java.awt.Color(245, 245, 245));

        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Xử lý sự kiện khi nhấn Enter trong textField1
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(0, 0, 25, getHeight());
        g2.fillRect(getWidth() - 25, getHeight() - 25, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public com.example.project_2.components.swing.Button button1;
    public com.example.project_2.components.swing.TextField textField1;
    // End of variables declaration//GEN-END:variables
}
