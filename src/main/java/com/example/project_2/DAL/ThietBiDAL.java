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
 *
 * @author huynh
 */
public class ThietBiDAL extends BaseDAL<ThietBi>{
    public ThietBiDAL() {
        super(ThietBi.class);
    }
    
    //Thống kê thiết bị được mượn theo: thời gian, tên thiết bị
    public List<Object[]> thongKeThietBiDuocMuon(LocalDateTime startTime, LocalDateTime endTime, String maTB) {
        // language=HQL
        String hqlQuery = "SELECT DATE_FORMAT(tt.TGMuon, '%d-%m-%Y'), COUNT(tt) " +
                          "FROM ThongTinSD tt " +
                          "WHERE (tt.TGMuon IS NOT NULL OR tt.TGTra IS NULL) " +
                          ("-1".equals(maTB) ? "" : "AND tt.thietBi.MaTB=:maTB ") +
                          "AND (tt.TGMuon BETWEEN :startTime AND :endTime)" +
                          "GROUP BY DATE_FORMAT(tt.TGMuon, '%d-%m-%Y')";
        
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("startTime", startTime);
        parameters.put("endTime", endTime);
        if (!"-1".equals(maTB)) {
            parameters.put("maTB", maTB);
        }

        return executeQuery(hqlQuery, Object[].class, parameters);
    }
}
