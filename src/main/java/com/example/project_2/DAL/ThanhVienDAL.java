/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.DAL;

import com.example.project_2.DTO.ThanhVien;
import java.util.List;
import org.hibernate.query.Query;

/**
 *
 * @author huynh
 */
public class ThanhVienDAL extends BaseDAL<ThanhVien> {
    public ThanhVienDAL() {
        super(ThanhVien.class);
    }
    
    //Thống kê số lượng thành viên vào khu học tập theo: thời gian, khoa, ngành.
    public List<Object[]> thongKeSoLuongThanhVien() {
        openSession(); // Mở session
        try {
            // Sử dụng HQL để thực hiện truy vấn
            String hqlQuery = "SELECT tv.khoa, tv.nganh, COUNT(tt) "
                            + "FROM ThanhVien tv "
                            + "INNER JOIN tv.thongTinSD tt "
                            + "GROUP BY tv.khoa, tv.nganh";

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
