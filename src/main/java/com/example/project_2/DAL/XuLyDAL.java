package com.example.project_2.DAL;

import com.example.project_2.DTO.XuLy;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.query.Query;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author huynh
 */
public class XuLyDAL extends BaseDAL<XuLy>{
    public XuLyDAL() {
        super(XuLy.class);
    }
    
    public List<XuLy> layThongTinViPhamVaTongTien() {
        List<XuLy> thongTinViPhamVaTien = new ArrayList<>();
        openSession();

        try {
            String hqlQuery = "SELECT xl, SUM(xl.SoTien) "
                            + "FROM XuLy xl "
                            + "WHERE xl.TrangThaiXL = 1 "
                            + "GROUP BY xl";
            Query<Object[]> query = session.createQuery(hqlQuery, Object[].class);

            List<Object[]> results = query.getResultList();

            for (Object[] result : results) {
                if (result[0] != null && result[1] != null) {
                    XuLy xuLy = (XuLy) result[0];
                    Long tongTienLong = (Long) result[1];
                    xuLy.setTongTien(tongTienLong);
                    thongTinViPhamVaTien.add(xuLy);
                }
            }
        } catch (Exception e) {
            System.out.println("Error while retrieving information: " + e);
        } finally {
            closeSession();
        }

        return thongTinViPhamVaTien;
    }

    
    public List<XuLy> thongKeXuLyDangXuLy() {
        List<XuLy> danhSachXuLy = new ArrayList<>();
        openSession();

        try {
            String hqlQuery = "FROM XuLy xl WHERE xl.TrangThaiXL = 0";
            Query<XuLy> query = session.createQuery(hqlQuery, XuLy.class);
            danhSachXuLy = query.getResultList();
        } catch (Exception e) {
            System.out.println("Error while retrieving information: " + e);
        } finally {
            closeSession();
        }

        return danhSachXuLy;
    }
}