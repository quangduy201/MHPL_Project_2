/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.DTO;

/**
 *
 * @author Hung
 */
import jakarta.persistence.*;

@Entity
@Table(name = "thietbi")
public class ThietBi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MaTB;

    @Column(name = "TenTB", nullable = false)
    private String TenTB;

    @Column(name = "MoTaTB", length = 2000)
    private String MoTaTB;

    public ThietBi() {
    }
    
    public ThietBi(int MaTB, String TenTB, String MoTaTB) {
        this.MaTB = MaTB;
        this.TenTB = TenTB;
        this.MoTaTB = MoTaTB;
    }
    
    public int getMaTB() {
        return MaTB;
    }

    public void setMaTB(int MaTB) {
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

    
}
