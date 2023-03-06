package com.hepexta.todoapp.repository.impl;

import com.hepexta.todoapp.model.db.TodoItem;
import com.hepexta.todoapp.repository.TodoRepository;
import com.hepexta.todoapp.repository.mapper.TodoItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepository {

    private final TodoItemMapper todoItemMapper;
    @Override
    public List<TodoItem> upsertAll(List<TodoItem> entityList) {
        todoItemMapper.upsert(entityList);
        return entityList;
    }

    @Override
    public TodoItem upsert(TodoItem entity) {
        upsertAll(List.of(entity));
        return entity;
    }

    @Override
    public List<TodoItem> find(TodoItem entity) {
        return todoItemMapper.find(entity);
    }

    @Override
    public void delete(TodoItem entity) {
        todoItemMapper.delete(entity);
    }
}
