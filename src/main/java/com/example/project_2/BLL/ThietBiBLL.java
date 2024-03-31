package com.example.project_2.BLL;

import com.example.project_2.DAL.ThietBiDAL;
import com.example.project_2.DTO.ThietBi;

import java.util.List;

public class ThietBiBLL extends BaseBLL<ThietBi> {
    private final ThietBiDAL thietBiDAL;

    public ThietBiBLL() {
        super(new ThietBiDAL());
        thietBiDAL = new ThietBiDAL();
    }

    //Thống kê thiết bị được mượn theo: thời gian, tên thiết bị
    public List<Object[]> thongKeThietBiDuocMuon() {
        return thietBiDAL.thongKeThietBiDuocMuon();
    }
}
