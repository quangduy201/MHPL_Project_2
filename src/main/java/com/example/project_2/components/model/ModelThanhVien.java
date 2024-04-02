/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.components.model;

/**
 *
 * @author Hung
 */
public class ModelThanhVien {
    private String khoa;
    private int total;

    public ModelThanhVien(String khoa, int total) {
        this.khoa = khoa;
        this.total = total;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
