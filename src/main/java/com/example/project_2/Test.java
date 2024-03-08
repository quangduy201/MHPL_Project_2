package com.example.project_2;

import com.example.project_2.utils.HibernateUtil;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        try {
            HibernateUtil.initialize();
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(Project_2.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }

    public static void shutdown() {
        HibernateUtil.shutdown();
    }
}
