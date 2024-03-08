package com.example.project_2;

import com.example.project_2.utils.HibernateUtil;

import javax.swing.*;

public class Project_2 {
    public static void main(String[] args) {
        HibernateUtil.initialize();
        Project_2.initialize();
    }

    public static void initialize() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    UIManager.put("OptionPane.cancelButtonText", "Hủy");
                    UIManager.put("OptionPane.okButtonText", "Đồng ý");
//                    new MainGUI().show();
                    break;
                }
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Project_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public static void shutdown() {
        HibernateUtil.shutdown();
        System.exit(0);
    }
}
