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
    @Column(name = "MaXL", nullable = false)
    private Integer MaXL;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MaTV", nullable = false)
    private ThanhVien thanhVien;

    @Column(name = "HinhThucXL", length = 250)
    private String HinhThucXL;

    @Column(name = "SoTien")
    private Integer SoTien;

    @Column(name = "NgayXL")
    private LocalDateTime NgayXL;

    @Column(name = "TrangThaiXL")
    private Integer TrangThaiXL;
    
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

    public Integer getMaXL() {
        return MaXL;
    }

    public void setMaXL(Integer MaXL) {
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

    public Integer getSoTien() {
        return SoTien;
    }

    public void setSoTien(Integer soTien) {
        this.SoTien = soTien;
    }

    public LocalDateTime getNgayXL() {
        return NgayXL;
    }

    public void setNgayXL(LocalDateTime NgayXL) {
        this.NgayXL = NgayXL;
    }

    public Integer getTrangThaiXL() {
        return TrangThaiXL;
    }

    public void setTrangThaiXL(Integer TrangThaiXL) {
        this.TrangThaiXL = TrangThaiXL;
    }

    public Long getTongTien() {
        return TongTien;
    }

    public void setTongTien(Long TongTien) {
        this.TongTien = TongTien;
    }

}