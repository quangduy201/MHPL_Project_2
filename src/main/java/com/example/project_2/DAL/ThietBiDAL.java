/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.DAL;

import com.example.project_2.DTO.ThietBi;

import java.util.List;

/**
 *
 * @author huynh
 */
public class ThietBiDAL extends BaseDAL<ThietBi>{
    public ThietBiDAL() {
        super(ThietBi.class);
    }
    
    //Thống kê thiết bị được mượn theo: thời gian, tên thiết bị
    public List<Object[]> thongKeThietBiDuocMuon() {
        // language=HQL
        String hqlQuery = "SELECT tt.thietBi.TenTB, COUNT(tt) "
                + "FROM ThongTinSD tt "
                + "WHERE tt.TGMuon IS NOT NULL AND tt.TGTra IS NULL "
                + "GROUP BY tt.thietBi.TenTB";

        return executeQuery(hqlQuery, Object[].class, null);
    }
}
