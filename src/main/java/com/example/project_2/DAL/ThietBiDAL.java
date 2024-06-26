/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.DAL;

import com.example.project_2.DTO.ThietBi;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huynh
 */
public class ThietBiDAL extends BaseDAL<ThietBi> {

    public ThietBiDAL() {
        super(ThietBi.class);
    }

    public List<ThietBi> getThietBiDangDuocMuonByMaTV(Long MaTV) {
        // language=HQL
        String hqlQuery = "SELECT tt.thietBi " +
                "FROM ThongTinSD tt " +
                "WHERE tt.thanhVien.MaTV = :MaTV AND TGMuon IS NOT NULL AND TGTra IS NULL ";

        Map<String, Object> params = new HashMap<>();
        params.put("MaTV", MaTV);

        return executeQuery(hqlQuery, ThietBi.class, params);
    }

    //Thống kê thiết bị được mượn theo: thời gian, tên thiết bị
    public List<Object[]> thongKeThietBiDaDuocMuon(LocalDateTime startTime, LocalDateTime endTime, String maTB, boolean isTable) {
        // language=HQL
        String hqlQuery;

        if (isTable) {
            hqlQuery = "SELECT tt.thietBi.MaTB, tt.thietBi.TenTB, tt.thanhVien.MaTV, tt.thanhVien.HoTen, DATE_FORMAT(tt.TGMuon, '%d-%m-%Y %H:%i:%s'), DATE_FORMAT(tt.TGTra, '%d-%m-%Y %H:%i:%s') " +
                    "FROM ThongTinSD tt " +
                    "WHERE (tt.TGMuon IS NOT NULL AND tt.TGTra IS NOT NULL) " +
                    ("-1".equals(maTB) ? "" : "AND tt.thietBi.MaTB=:maTB ") +
                    "AND ((tt.TGMuon BETWEEN :startTime AND :endTime) " +
                    "OR (tt.TGTra BETWEEN :startTime AND :endTime)) " +
                    "ORDER BY DATE_FORMAT(tt.TGMuon, '%d-%m-%Y %H:%i:%s') ASC, DATE_FORMAT(tt.TGTra, '%d-%m-%Y %H:%i:%s') ASC";
        } else {
            hqlQuery = "SELECT DATE_FORMAT(tt.TGMuon, '%d-%m-%Y'), COUNT(tt) " +
                    "FROM ThongTinSD tt " +
                    "WHERE (tt.TGMuon IS NOT NULL AND tt.TGTra IS NOT NULL) " +
                    ("-1".equals(maTB) ? "" : "AND tt.thietBi.MaTB=:maTB ") +
                    "AND ((tt.TGMuon BETWEEN :startTime AND :endTime) " +
                    "OR (tt.TGTra BETWEEN :startTime AND :endTime)) " +
                    "GROUP BY DATE_FORMAT(tt.TGMuon, '%d-%m-%Y') " +
                    "ORDER BY DATE_FORMAT(tt.TGMuon, '%d-%m-%Y') ASC";
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("startTime", startTime);
        parameters.put("endTime", endTime);
        if (!"-1".equals(maTB)) {
            parameters.put("maTB", maTB);
        }

        return executeQuery(hqlQuery, Object[].class, parameters);
    }

    //thống kê thiết bị đang mượn theo: thời gian,tên thiết bị
    public List<Object[]> thongKeThietBiDangMuon(LocalDateTime startTime, LocalDateTime endTime, String maTB, boolean isTable) {
        String hqlQuery = "";
        // language=HQL
        if (isTable) {
            hqlQuery = "SELECT tt.thietBi.MaTB, tt.thietBi.TenTB, tt.thanhVien.MaTV, tt.thanhVien.HoTen, DATE_FORMAT(tt.TGMuon, '%d-%m-%Y %H:%i:%s') "
                    + "FROM ThongTinSD tt "
                    + "WHERE (tt.TGMuon IS NOT NULL AND tt.TGTra IS NULL) "
                    + ("-1".equals(maTB) ? "" : "AND tt.thietBi.MaTB=:maTB ")
                    + "AND (tt.TGMuon BETWEEN :startTime AND :endTime) "
                    + "ORDER BY DATE_FORMAT(tt.TGMuon, '%d-%m-%Y %H:%i:%s') ASC";
        } else {
            hqlQuery = "SELECT DATE_FORMAT(tt.TGMuon, '%d-%m-%Y'), COUNT(tt) "
                    + "FROM ThongTinSD tt "
                    + "WHERE (tt.TGMuon IS NOT NULL AND tt.TGTra IS NULL) "
                    + ("-1".equals(maTB) ? "" : "AND tt.thietBi.MaTB=:maTB ")
                    + "AND (tt.TGMuon BETWEEN :startTime AND :endTime) "
                    + "GROUP BY DATE_FORMAT(tt.TGMuon, '%d-%m-%Y') "
                    + "ORDER BY DATE_FORMAT(tt.TGMuon, '%d-%m-%Y') ASC";
        }
        Map<String, Object> parameters = new HashMap<>();
        if (!"-1".equals(maTB)) {
            parameters.put("maTB", maTB);
        }
        parameters.put("startTime", startTime);
        parameters.put("endTime", endTime);

        return executeQuery(hqlQuery, Object[].class, parameters);
    }

    public boolean deleteAllById(String id) {
        char firstDigit = id.charAt(0);

        // language=HQL
        String hqlQuery = "DELETE FROM ThietBi WHERE SUBSTRING(id, 1, 1) = :firstDigit";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("firstDigit", Character.toString(firstDigit));

        int rowsAffected = executeUpdate(hqlQuery, parameters);

        return rowsAffected > 0;
    }
}
