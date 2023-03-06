package com.hepexta.todoapp.repository.mapper;

import java.util.List;

public interface BaseMapper<T> {
    void upsert(List<T> entityList);

    List<T> find(T entity);

    void delete(T entity);
}
