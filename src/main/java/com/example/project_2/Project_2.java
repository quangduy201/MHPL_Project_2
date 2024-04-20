package com.example.project_2;

import com.example.project_2.GUI.Main;
import com.example.project_2.utils.HibernateUtil;

public class Project_2 {
    public static void main(String[] args) {
        HibernateUtil.initialize();
        Project_2.initialize();
    }

    public static void initialize() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    new Main().setVisible(true);
                    break;
                }
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Project_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public static void shutdown() {
        HibernateUtil.shutdown();
        System.exit(0);
    }
}
