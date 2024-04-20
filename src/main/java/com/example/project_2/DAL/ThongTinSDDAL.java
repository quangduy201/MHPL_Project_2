/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.DAL;

import com.example.project_2.DTO.ThongTinSD;

import java.util.List;
import java.util.Map;

/**
 * @author huynh
 */
public class ThongTinSDDAL extends BaseDAL<ThongTinSD> {
    public ThongTinSDDAL() {
        super(ThongTinSD.class);
    }

    public List<ThongTinSD> getThongTinSDMuonTra(Map<String, Object> criteria) {
        // language=HQL
        String hqlQuery = "SELECT tt " +
                "FROM ThongTinSD tt " +
                "WHERE tt.thietBi IS NOT NULL " +
                (criteria.isEmpty()
                        ? ""
                        : "AND (tt.thanhVien.HoTen LIKE :HoTen OR tt.thietBi.TenTB LIKE :TenTB) ") +
                "ORDER BY CASE " +
                "WHEN tt.TGDatcho IS NOT NULL THEN 1 " +
                "WHEN tt.TGTra IS NULL THEN 0 " +
                "ELSE 2 " +
                "END, tt.TGTra DESC";

        return executeQuery(hqlQuery, ThongTinSD.class, criteria);
    }

    // Thống kê thiết bị đang được mượn và đang được mượn theo thời gian.
    public List<Object[]> thongKeThietBiMuon() {
        // language=HQL
        String hqlQuery = "SELECT sd.thietBi.TenTB, "
                + "COUNT(CASE WHEN sd.TGMuon IS NOT NULL AND sd.TGTra IS NOT NULL THEN 1 END) AS DaTra, "
                + "COUNT(CASE WHEN sd.TGMuon IS NOT NULL AND sd.TGTra IS NULL THEN 1 END) AS DangMuon "
                + "FROM ThongTinSD sd "
                + "GROUP BY sd.thietBi.TenTB";

        return executeQuery(hqlQuery, Object[].class, null);
    }
}
