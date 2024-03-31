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
@Table(name = "thongtinsd")
public class ThongTinSD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MaTT;

    @ManyToOne
    @JoinColumn(name = "MaTV")
    private ThanhVien thanhVien;

    @ManyToOne
    @JoinColumn(name = "MaTB")
    private ThietBi thietBi;

    @Column(name = "TGVao")
    private LocalDateTime TGVao;

    @Column(name = "TGMuon")
    private LocalDateTime TGMuon;

    @Column(name = "TGTra")
    private LocalDateTime TGTra;
    
    public ThongTinSD() {
    }
    
    public ThongTinSD(int MaTT, ThanhVien thanhVien, ThietBi thietBi, LocalDateTime TGVao, LocalDateTime TGMuon, LocalDateTime TGTra) {
        this.MaTT = MaTT;
        this.thanhVien = thanhVien;
        this.thietBi = thietBi;
        this.TGVao = TGVao;
        this.TGMuon = TGMuon;
        this.TGTra = TGTra;
    }

    public int getMaTT() {
        return MaTT;
    }

    public void setMaTT(int MaTT) {
        this.MaTT = MaTT;
    }

    public ThanhVien getThanhVien() {
        return thanhVien;
    }

    public void setThanhVien(ThanhVien thanhVien) {
        this.thanhVien = thanhVien;
    }

    public ThietBi getThietBi() {
        return thietBi;
    }

    public void setThietBi(ThietBi thietBi) {
        this.thietBi = thietBi;
    }

    public LocalDateTime getTGVao() {
        return TGVao;
    }

    public void setTGVao(LocalDateTime TGVao) {
        this.TGVao = TGVao;
    }

    public LocalDateTime getTGMuon() {
        return TGMuon;
    }

    public void setTGMuon(LocalDateTime TGMuon) {
        this.TGMuon = TGMuon;
    }

    public LocalDateTime getTGTra() {
        return TGTra;
    }

    public void setTGTra(LocalDateTime TGTra) {
        this.TGTra = TGTra;
    }
}
