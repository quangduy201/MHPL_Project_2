/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.DAL;

import com.example.project_2.DTO.ThongTinSD;
import java.util.List;
import org.hibernate.query.Query;

/**
 *
 * @author huynh
 */
public class ThongTinSDDAL extends BaseDAL<ThongTinSD> {
    public ThongTinSDDAL() {
        super(ThongTinSD.class);
    }
    
    //Thống kê thiết bị đang được mượn và đang được mượn theo thời gian.
    public List<Object[]> thongKeThietBiMuon() {
        openSession();
        try {
            String hqlQuery = "SELECT sd.thietBi.TenTB, "
                            + "COUNT(CASE WHEN sd.TGMuon IS NOT NULL AND sd.TGTra IS NOT NULL THEN 1 END) AS DaTra, "
                            + "COUNT(CASE WHEN sd.TGMuon IS NOT NULL AND sd.TGTra IS NULL THEN 1 END) AS DangMuon "
                            + "FROM ThongTinSD sd "
                            + "GROUP BY sd.thietBi.TenTB";

            Query<Object[]> query = session.createQuery(hqlQuery, Object[].class);
            List<Object[]> results = query.getResultList();
            return results;
        } catch (Exception e) {
            System.out.println("Error while performing thongKeThietBiMuon: " + e);
            return null;
        } finally {
            closeSession();
        }
    }
}
