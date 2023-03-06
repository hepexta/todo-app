package com.hepexta.todoapp.repository.impl;

import com.hepexta.todoapp.model.db.Category;
import com.hepexta.todoapp.model.db.TodoItem;
import com.hepexta.todoapp.repository.CategoryRepository;
import com.hepexta.todoapp.repository.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryMapper categoryMapper;
    @Override
    public List<Category> upsertAll(List<Category> entityList) {
        categoryMapper.upsert(entityList);
        return entityList;
    }

    @Override
    public Category upsert(Category entity) {
        upsertAll(List.of(entity));
        return entity;
    }

    @Override
    public List<Category> find(Category entity) {
        return categoryMapper.find(entity);
    }

    @Override
    public void delete(Category entity) {
        categoryMapper.delete(entity);
    }
}
