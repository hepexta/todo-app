package com.hepexta.todoapp.repository.mapper;

import com.hepexta.todoapp.model.db.TodoItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoItemMapper extends BaseMapper<TodoItem> {

}
