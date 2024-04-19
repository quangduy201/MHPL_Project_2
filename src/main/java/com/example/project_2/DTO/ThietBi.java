/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.DTO;

import jakarta.persistence.*;

/**
 *
 * @author Hung
 */
@Entity
@Table(name = "thietbi")
public class ThietBi {
    @Id
    @Column(name = "MaTB", nullable = false)
    private long MaTB;

    @Column(name = "TenTB", nullable = false, length = 100)
    private String TenTB;

    @Column(name = "MoTaTB", length = 2000)
    private String MoTaTB;

    public ThietBi() {
    }
    
    public ThietBi(long MaTB, String TenTB, String MoTaTB) {
        this.MaTB = MaTB;
        this.TenTB = TenTB;
        this.MoTaTB = MoTaTB;
    }
    
    public long getMaTB() {
        return MaTB;
    }

    public void setMaTB(long MaTB) {
        this.MaTB = MaTB;
    }

    public String getTenTB() {
        return TenTB;
    }

    public void setTenTB(String TenTB) {
        this.TenTB = TenTB;
    }

    public String getMoTaTB() {
        return MoTaTB;
    }

    public void setMoTaTB(String MoTaTB) {
        this.MoTaTB = MoTaTB;
    }

    @Override
    public String toString() {
        if (MaTB == -1)
            return TenTB;
        return MaTB + " - " + TenTB;
    }
}
