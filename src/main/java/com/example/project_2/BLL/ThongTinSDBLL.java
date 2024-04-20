package com.example.project_2.BLL;

import com.example.project_2.DAL.ThongTinSDDAL;
import com.example.project_2.DTO.ThongTinSD;

import java.util.List;
import java.util.Map;

/**
 * @author huynh
 */
public class ThongTinSDBLL extends BaseBLL<ThongTinSD> {
    private final ThongTinSDDAL thongTinSDDAL;

    public ThongTinSDBLL() {
        super(new ThongTinSDDAL());
        thongTinSDDAL = (ThongTinSDDAL) getDAL();
    }

    public List<ThongTinSD> getThongTinSDMuonTra(Map<String, Object> criteria) {
        return thongTinSDDAL.getThongTinSDMuonTra(criteria);
    }

    // Thống kê thiết bị đang được mượn và đang được mượn theo thời gian.
    public List<Object[]> thongKeThietBiMuon() {
        return thongTinSDDAL.thongKeThietBiMuon();
    }
}
