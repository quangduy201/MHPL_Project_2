package com.example.project_2.DTO;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hung
 */
@Entity
@Table(name = "thanhvien")
public class ThanhVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MaTV;

    @Column(name = "HoTen", nullable = false)
    private String HoTen;

    @Column(name = "Khoa", length = 100)
    private String Khoa;

    @Column(name = "Nganh", length = 100)
    private String Nganh;
    
    @Column(name = "SDT")
    private int SDT;
    
    public ThanhVien() {
    }
    
    public ThanhVien(int MaTV, String HoTen, String Khoa, String Nganh, int SDT) {
        this.MaTV = MaTV;
        this.HoTen = HoTen;
        this.Khoa = Khoa;
        this.Nganh = Nganh;
        this.SDT = SDT;
    }

    public int getMaTV() {
        return MaTV;
    }

    public void setMaTV(int MaTV) {
        this.MaTV = MaTV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getKhoa() {
        return Khoa;
    }

    public void setKhoa(String Khoa) {
        this.Khoa = Khoa;
    }

    public String getNganh() {
        return Nganh;
    }

    public void setNganh(String Nganh) {
        this.Nganh = Nganh;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    
    
}