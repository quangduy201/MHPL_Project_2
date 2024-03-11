package com.example.project_2.BLL;

import com.example.project_2.DAL.BaseDAL;

public class BaseBLL<DTO> {
    private final BaseDAL<DTO> dal;

    public BaseBLL(BaseDAL<DTO> dal) {
        this.dal = dal;
    }

    public BaseDAL<DTO> getDAL() {
        return dal;
    }

    public boolean add(DTO object) {
        return dal.persist(object);
    }

    public boolean update(DTO object) {
        return dal.merge(object);
    }

    public boolean delete(DTO object) {
        return dal.remove(object);
    }

    public long count() {
        return dal.count();
    }

}