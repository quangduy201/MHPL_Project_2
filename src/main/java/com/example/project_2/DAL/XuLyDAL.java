/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.DAL;

import com.example.project_2.DTO.XuLy;
import java.time.LocalDateTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author huynh
 */
public class XuLyDAL extends BaseDAL<XuLy>{
    public XuLyDAL() {
        super(XuLy.class);
    }

    public List<Object[]> thongKeXuLy(LocalDateTime startTime, LocalDateTime endTime, int trangThai, boolean isTable) {
        String hqlQuery = "";
        
        if (isTable) {
            hqlQuery = "SELECT xl.MaXL, xl.thanhVien.HoTen, xl.HinhThucXL, xl.SoTien, DATE_FORMAT(xl.NgayXL, '%d-%m-%Y') "
                + "FROM XuLy xl WHERE " + (trangThai != -1 ? "xl.TrangThaiXL = " + trangThai + " AND " : "")
                + "(xl.NgayXL BETWEEN :startTime AND :endTime)";


        } else {
            hqlQuery = "SELECT DATE_FORMAT(xl.NgayXL, '%d-%m-%Y'), count(xl.MaXL), count(xl.SoTien) "
                + "FROM XuLy xl WHERE " + (trangThai != -1 ? "xl.TrangThaiXL = " + trangThai + " AND " : "")
                + "(xl.NgayXL BETWEEN :startTime AND :endTime) "
                + "GROUP BY DATE_FORMAT(xl.NgayXL, '%d-%m-%Y')";
        
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("startTime", startTime);
        parameters.put("endTime", endTime);

        return executeQuery(hqlQuery, Object[].class, parameters);
    }
}