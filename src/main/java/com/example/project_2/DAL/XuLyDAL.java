/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.DAL;

import com.example.project_2.DTO.XuLy;

import java.util.ArrayList;
import java.util.List;

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
        // language=HQL
        String hqlQuery = "SELECT xl, SUM(xl.SoTien) " +
                "FROM XuLy xl " +
                "WHERE xl.TrangThaiXL = 1 " +
                "GROUP BY xl";

        List<Object[]> results = executeQuery(hqlQuery, Object[].class, null);
        for (Object[] result : results) {
            if (result[0] != null && result[1] != null) {
                XuLy xuLy = (XuLy) result[0];
                Long tongTienLong = (Long) result[1];
                xuLy.setTongTien(tongTienLong);
                thongTinViPhamVaTien.add(xuLy);
            }
        }
        return thongTinViPhamVaTien;
    }

    public List<XuLy> thongKeXuLyDangXuLy() {
        List<XuLy> danhSachXuLy;
        // language=HQL
        String hqlQuery = "FROM XuLy xl WHERE xl.TrangThaiXL = 0";

        danhSachXuLy = executeQuery(hqlQuery, XuLy.class, null);
        return danhSachXuLy;
    }
}