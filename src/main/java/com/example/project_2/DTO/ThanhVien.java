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
    private Long MaTV;

    @Column(name = "HoTen", nullable = false, length = 100)
    private String HoTen;
    
    @Column(name = "Email", nullable = false, length = 25)
    private String Email;
    
    @Column(name = "Password", length = 10)
    private String Password;

    @Column(name = "Khoa", length = 100)
    private String Khoa;

    @Column(name = "Nganh", length = 100)
    private String Nganh;

    @Column(name = "SDT", length = 15)
    private String SDT;

    @OneToMany(mappedBy = "thanhVien", orphanRemoval = true)
    private Set<ThongTinSD> ThongTinSD = new LinkedHashSet<>();

    public ThanhVien() {
    }
    
    public ThanhVien(Long MaTV, String HoTen, String Email, String Password, String Khoa, String Nganh, String SDT) {
        this.MaTV = MaTV;
        this.HoTen = HoTen;
        this.Khoa = Khoa;
        this.Nganh = Nganh;
        this.Email = Email;
        this.Password = Password;
        this.SDT = SDT;
    }

    public Long getMaTV() {
        return MaTV;
    }

    public void setMaTV(Long MaTV) {
        this.MaTV = MaTV;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
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

    public Set<ThongTinSD> getThongTinSD() {
        return ThongTinSD;
    }

    public void setThongTinSD(Set<ThongTinSD> ThongTinSD) {
        this.ThongTinSD = ThongTinSD;
    }

}