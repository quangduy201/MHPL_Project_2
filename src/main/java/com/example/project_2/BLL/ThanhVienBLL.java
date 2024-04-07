/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.BLL;
import com.example.project_2.DAL.ThanhVienDAL;
import com.example.project_2.DTO.ThanhVien;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author huynh
 */
public class ThanhVienBLL extends BaseBLL<ThanhVien> {
    private final ThanhVienDAL thanhVienDAL;

    public ThanhVienBLL() {
        super(new ThanhVienDAL());
        thanhVienDAL = (ThanhVienDAL) getDAL();
    }

    public List<Object[]> thongKeSoLuongThanhVien(LocalDateTime startTime, LocalDateTime endTime, String khoa, String nganh) {
        return thanhVienDAL.thongKeSoLuongThanhVien(startTime, endTime, khoa, nganh, false);
    }
    
    public List<Object[]> thongKeSoLuongThanhVienForTable(LocalDateTime startTime, LocalDateTime endTime, String khoa, String nganh) {
        return thanhVienDAL.thongKeSoLuongThanhVien(startTime, endTime, khoa, nganh, true);
    }
    
    public List<String> layDanhSachKhoa() {
        return thanhVienDAL.layDanhSachKhoa();
    }
    
    public List<String> layDanhSachNganh() {
        return thanhVienDAL.layDanhSachNganh();
    }
}