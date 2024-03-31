package com.example.project_2.BLL;

import com.example.project_2.DAL.ThongTinSDDAL;
import com.example.project_2.DTO.ThongTinSD;

import java.util.List;

/**
 *
 * @author huynh
 */
public class ThongTinSDBLL extends BaseBLL<ThongTinSD> {
    private final ThongTinSDDAL thongTinSDDAL;

    public ThongTinSDBLL() {
        super(new ThongTinSDDAL());
        thongTinSDDAL = (ThongTinSDDAL) getDAL();
    }
    
    // Thống kê thiết bị đang được mượn và đang được mượn theo thời gian.
    public List<Object[]> thongKeThietBiMuon() {
        return thongTinSDDAL.thongKeThietBiMuon();
    }
}
