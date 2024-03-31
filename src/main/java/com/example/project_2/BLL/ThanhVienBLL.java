/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.BLL;
import com.example.project_2.DAL.ThanhVienDAL;
import com.example.project_2.DTO.ThanhVien;
import java.util.List;

/**
 *
 * @author huynh
 */
public class ThanhVienBLL extends BaseBLL<ThanhVien> {
    private final ThanhVienDAL thanhVienDAL;

    public ThanhVienBLL() {
        super(new ThanhVienDAL());
        thanhVienDAL = new ThanhVienDAL();
    }

    //Thống kê thiết bị được mượn theo: thời gian, tên thiết bị
    public List<Object[]> thongKeSoLuongThanhVien() {
        return thanhVienDAL.thongKeSoLuongThanhVien();
    }
}