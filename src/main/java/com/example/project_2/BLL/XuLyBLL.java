/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.BLL;

import com.example.project_2.DAL.XuLyDAL;
import com.example.project_2.DTO.XuLy;

import java.util.List;

/**
 *
 * @author huynh
 */
public class XuLyBLL extends BaseBLL<XuLy> {
    private final XuLyDAL xuLyDAL;

    public XuLyBLL() {
        super(new XuLyDAL());
        xuLyDAL = (XuLyDAL) getDAL();
    }    
    
    public List<XuLy> layThongTinViPhamVaTongTien() {
        return xuLyDAL.layThongTinViPhamVaTongTien();
    }
    
    public List<XuLy> thongKeXuLyDangXuLy() {
        return xuLyDAL.thongKeXuLyDangXuLy();
    }
}
