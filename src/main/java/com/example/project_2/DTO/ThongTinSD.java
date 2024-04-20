/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.DTO;

import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Hung
 */
@Entity
@Table(name = "thongtinsd")
public class ThongTinSD {
    @Id
    @Column(name = "MaTT", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer MaTT;

    @ManyToOne(optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "MaTV", nullable = false)
    private ThanhVien thanhVien;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "MaTB")
    private ThietBi thietBi;

    @Column(name = "TGVao")
    private LocalDateTime TGVao;

    @Column(name = "TGMuon")
    private LocalDateTime TGMuon;

    @Column(name = "TGTra")
    private LocalDateTime TGTra;
    
    @Column(name = "TGDatcho")
    private LocalDateTime TGDatcho;
    
    public ThongTinSD() {
    }
    
    public ThongTinSD(Integer MaTT, ThanhVien thanhVien, ThietBi thietBi, LocalDateTime TGVao, LocalDateTime TGMuon, LocalDateTime TGTra, LocalDateTime TGDatcho) {
        this.MaTT = MaTT;
        this.thanhVien = thanhVien;
        this.thietBi = thietBi;
        this.TGVao = TGVao;
        this.TGMuon = TGMuon;
        this.TGTra = TGTra;
        this.TGDatcho = TGDatcho;
    }

    public Integer getMaTT() {
        return MaTT;
    }

    public void setMaTT(Integer MaTT) {
        this.MaTT = MaTT;
    }

    public ThanhVien getThanhVien() {
        return thanhVien;
    }

    public void setThanhVien(ThanhVien MaTV) {
        this.thanhVien = MaTV;
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

    public LocalDateTime getTGDatcho() {
        return TGDatcho;
    }

    public void setTGDatcho(LocalDateTime TGDatcho) {
        this.TGDatcho = TGDatcho;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ThongTinSD that = (ThongTinSD) o;
        return getMaTT() != null && Objects.equals(getMaTT(), that.getMaTT());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}