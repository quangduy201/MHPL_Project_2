/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.DTO;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 *
 * @author Hung
 */
@Entity
@Table(name = "xuly")
public class XuLy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MaXL;

    @ManyToOne
    @JoinColumn(name = "MaTV")
    private ThanhVien thanhVien;

    @Column(name = "HinhThucXL", length = 250)
    private String HinhThucXL;

    @Column(name = "SoTien")
    private int SoTien;

    @Column(name = "NgayXL")
    private LocalDateTime NgayXL;

    @Column(name = "TrangThaiXL")
    private int TrangThaiXL;
    
    @Transient
    private Long TongTien;
    
    public XuLy() {
    }

    public XuLy(int MaXL, ThanhVien thanhVien, String HinhThucXL, int SoTien, LocalDateTime NgayXL, int TrangThaiXL) {
        this.MaXL = MaXL;
        this.thanhVien = thanhVien;
        this.HinhThucXL = HinhThucXL;
        this.SoTien = SoTien;
        this.NgayXL = NgayXL;
        this.TrangThaiXL = TrangThaiXL;
    }

    public int getMaXL() {
        return MaXL;
    }

    public void setMaXL(int MaXL) {
        this.MaXL = MaXL;
    }

    public ThanhVien getThanhVien() {
        return thanhVien;
    }

    public void setThanhVien(ThanhVien thanhVien) {
        this.thanhVien = thanhVien;
    }

    public String getHinhThucXL() {
        return HinhThucXL;
    }

    public void setHinhThucXL(String HinhThucXL) {
        this.HinhThucXL = HinhThucXL;
    }

    public int getSoTien() {
        return SoTien;
    }

    public void setSoTien(int SoTien) {
        this.SoTien = SoTien;
    }

    public LocalDateTime getNgayXL() {
        return NgayXL;
    }

    public void setNgayXL(LocalDateTime NgayXL) {
        this.NgayXL = NgayXL;
    }

    public int getTrangThaiXL() {
        return TrangThaiXL;
    }

    public void setTrangThaiXL(int TrangThaiXL) {
        this.TrangThaiXL = TrangThaiXL;
    }

    public Long getTongTien() {
        return TongTien;
    }

    public void setTongTien(Long tongTien) {
        this.TongTien = tongTien;
    }
    
}