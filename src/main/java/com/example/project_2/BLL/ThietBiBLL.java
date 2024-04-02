package com.example.project_2.BLL;

import com.example.project_2.DAL.ThietBiDAL;
import com.example.project_2.DTO.ThietBi;
import java.time.LocalDateTime;

import java.util.List;

/**
 *
 * @author huynh
 */
public class ThietBiBLL extends BaseBLL<ThietBi> {
    private final ThietBiDAL thietBiDAL;

    public ThietBiBLL() {
        super(new ThietBiDAL());
        thietBiDAL = (ThietBiDAL) getDAL();
    }

    // Thống kê thiết bị được mượn theo: thời gian, tên thiết bị
    public List<Object[]> thongKeThietBiDuocMuon(LocalDateTime startTime, LocalDateTime endTime, String maTB) {
        return thietBiDAL.thongKeThietBiDuocMuon(startTime, endTime, maTB);
    }
}
