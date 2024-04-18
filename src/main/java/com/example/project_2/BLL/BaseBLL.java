package com.example.project_2.BLL;

import com.example.project_2.DAL.BaseDAL;

import java.util.List;
import java.util.Map;

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

    public List<DTO> getByPage(int pageNumber, int pageSize) {
        return dal.getByPage(pageNumber, pageSize);
    }
    
    public List<DTO> getAll() {
        return dal.getAll();
    }

    public DTO getById(Object id) {
        return dal.getById(id);
    }

    public List<DTO> getByCriteria(Map<String, Object> criteria) {
        return dal.getByCriteria(criteria);
    }
    
    public <T> List<T> search(Map<String, Object> searchCriteria, Class<T> entityClass) {
        return dal.search(searchCriteria, entityClass);
    }

}
