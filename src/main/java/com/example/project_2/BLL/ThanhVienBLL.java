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

    public String validateThanhVien(String MaTV, String HoTen, String Khoa, String Nganh, String SDT, String Email) {
        if (MaTV == null || MaTV.isBlank()) {
            return "Không được để trống mã thành viên.";
        }
        if (!MaTV.matches("^\\d{10}$")) {
            return "Mã thành viên phải là 10 chữ số.";
        }
        if (HoTen == null || HoTen.isBlank()) {
            return "Họ tên không được để trống.";
        }
        if (Khoa == null || Khoa.isBlank()) {
            return "Khoa không được để trống.";
        }
        if (Nganh == null || Nganh.isBlank()) {
            return "Ngành không được để trống.";
        }
        if (SDT == null || SDT.isBlank()) {
            return "SĐT không được để trống.";
        }
        if (!SDT.matches("^(\\+?84|0)[35789]\\d{8}$")) {
            return "SĐT không hợp lệ.";
        }
        if (Email == null || Email.isBlank()) {
            return "Email không được để trống.";
        }
        if (!Email.matches("^\\w+(\\.\\w+)*@\\w+(\\.\\w+)+")) {
            return "Email không hợp lệ.";
        }
        return null;
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