/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.DAL;

import com.example.project_2.DTO.ThanhVien;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author huynh
 */
public class ThanhVienDAL extends BaseDAL<ThanhVien> {
    public ThanhVienDAL() {
        super(ThanhVien.class);
    }

    // Thống kê số lượng thành viên vào khu học tập theo: thời gian, khoa, ngành.
    public List<Object[]> thongKeSoLuongThanhVien(LocalDateTime startTime, LocalDateTime endTime) {
        // language=HQL
        String hqlQuery = "SELECT tv.Khoa, tv.Nganh, COUNT(tt) " +
                "FROM ThongTinSD tt " +
                "INNER JOIN ThanhVien tv ON tt.thanhVien.MaTV = tv.MaTV " +
                "WHERE tt.TGVao BETWEEN :startTime AND :endTime " +
                "GROUP BY tv.Khoa, tv.Nganh";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("startTime", startTime);
        parameters.put("endTime", endTime);

        return executeQuery(hqlQuery, Object[].class, parameters);
    }
}
