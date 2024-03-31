/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.DAL;

import com.example.project_2.DTO.ThongTinSD;

import java.util.List;

/**
 *
 * @author huynh
 */
public class ThongTinSDDAL extends BaseDAL<ThongTinSD> {
    public ThongTinSDDAL() {
        super(ThongTinSD.class);
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
