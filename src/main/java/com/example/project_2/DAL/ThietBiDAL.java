/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.DAL;
import com.example.project_2.DTO.ThietBi;
import java.util.List;
import org.hibernate.query.Query;

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
        openSession();

        try {
            String hqlQuery = "SELECT tt.thietBi.TenTB, COUNT(tt) "
                            + "FROM ThongTinSD tt "
                            + "WHERE tt.TGMuon IS NOT NULL AND tt.TGTra IS NULL "
                            + "GROUP BY tt.thietBi.TenTB";

            Query<Object[]> query = session.createQuery(hqlQuery, Object[].class);

            List<Object[]> results = query.getResultList();
            return results;
        } catch (Exception e) {
            System.out.println("Error while querying database: " + e);
            return null;
        } finally {
            closeSession();
        }
    }
}
