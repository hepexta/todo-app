package com.hepexta.todoapp.repository;

import com.hepexta.todoapp.model.db.TodoItem;

import java.util.List;

public interface BaseRepository<T> {
    List<T> upsertAll(List<T> entityList);
    T upsert(T entity);
    List<T> find(T entity);

    void delete(T entity);
}
