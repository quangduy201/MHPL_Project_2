/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.DAL;

import com.example.project_2.DTO.ThanhVien;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    // Thống kê số lượng thành viên vào khu học tập theo: thời gian, khoa.
    public List<Object[]> thongKeSoLuongThanhVien(LocalDateTime startTime, LocalDateTime endTime, String khoa, String nganh, boolean isTable) {
        
        String hqlQuery = "";
        
        // language=HQL
        if (isTable) {
            hqlQuery = "SELECT tt.thanhVien.MaTV, tt.thanhVien.HoTen, tt.thanhVien.Khoa, tt.thanhVien.Nganh, DATE_FORMAT(tt.TGVao, '%d-%m-%Y %H:%i:%s')" +
                        "FROM ThongTinSD tt " +
                        "WHERE (tt.TGVao BETWEEN :startTime AND :endTime)" +
                        (khoa != null && !"".equals(khoa) ? " AND tt.thanhVien.Khoa = :khoa " : " ") +
                        (nganh != null && !"".equals(nganh) ? " AND tt.thanhVien.Nganh = :nganh " : " ") +
                        "ORDER BY DATE_FORMAT(tt.TGVao, '%d-%m-%Y %H:%i:%s') ASC";
        } else {
            hqlQuery = "SELECT DATE_FORMAT(tt.TGVao, '%d-%m-%Y'), COUNT(tt) " +
                        "FROM ThongTinSD tt " +
                        "WHERE (tt.TGVao BETWEEN :startTime AND :endTime)" +
                        (khoa != null && !"".equals(khoa) ? " AND tt.thanhVien.Khoa = :khoa " : " ") +
                        (nganh != null && !"".equals(nganh) ? " AND tt.thanhVien.Nganh = :nganh " : " ") +
                        "GROUP BY DATE_FORMAT(tt.TGVao, '%d-%m-%Y') " +
                        "ORDER BY DATE_FORMAT(tt.TGVao, '%d-%m-%Y') ASC";
        }
        

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("startTime", startTime);
        parameters.put("endTime", endTime);

        if (khoa != null && !"".equals(khoa)) {
            parameters.put("khoa", khoa);
        }
        
        if (nganh != null && !"".equals(nganh)) {
            parameters.put("nganh", nganh);
        }

        return executeQuery(hqlQuery, Object[].class, parameters);
    }
    
    public List<String> layDanhSachKhoa() {
        String hqlQuery = "SELECT DISTINCT tv.Khoa " +
                "FROM ThanhVien tv";
        
        return executeQuery(hqlQuery, String.class, null);
    }
    
    public List<String> layDanhSachNganh() {
        String hqlQuery = "SELECT DISTINCT tv.Nganh " +
                "FROM ThanhVien tv";
        
        return executeQuery(hqlQuery, String.class, null);
    }
    
}
