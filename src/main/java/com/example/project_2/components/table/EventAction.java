package com.example.project_2.components.table;


public interface EventAction<T> {

    public void delete(T student);

    public void update(T student);
}
