/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.DTO;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author Hung
 */
@Entity
@Table(name = "thanhvien")
public class ThanhVien {
    @Id
    @Column(name = "MaTV", nullable = false)
    private Integer MaTV;

    @Column(name = "HoTen", nullable = false, length = 100)
    private String HoTen;

    @Column(name = "Khoa", length = 100)
    private String Khoa;

    @Column(name = "Nganh", length = 100)
    private String Nganh;

    @Column(name = "SDT")
    private Integer SDT;

    @OneToMany(mappedBy = "thanhVien", orphanRemoval = true)
    private Set<ThongTinSD> ThongTinSD = new LinkedHashSet<>();

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

    public void setMaTV(Integer MaTV) {
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

    public Integer getSDT() {
        return SDT;
    }

    public void setSDT(Integer SDT) {
        this.SDT = SDT;
    }

    public Set<ThongTinSD> getThongTinSD() {
        return ThongTinSD;
    }

    public void setThongTinSD(Set<ThongTinSD> ThongTinSD) {
        this.ThongTinSD = ThongTinSD;
    }

}