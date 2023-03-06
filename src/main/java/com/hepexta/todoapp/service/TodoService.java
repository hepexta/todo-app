package com.hepexta.todoapp.service;

import com.hepexta.todoapp.model.db.TodoItem;
import com.hepexta.todoapp.model.db.TodoStatus;
import com.hepexta.todoapp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public List<TodoItem> getTodos() {
        return todoRepository.find(TodoItem.builder().build());
    }

    public TodoItem findById(UUID todoItemId) {
        return todoRepository.find(TodoItem.builder().todoItemId(todoItemId).build()).stream()
                .findFirst()
                .orElse(null);
    }

    public TodoItem upsert(TodoItem todo) {
        if (todo.getTodoItemId() == null) {
            todo.setTodoItemId(UUID.randomUUID());
        }
        if (todo.getStatus() == null) {
            todo.setStatus(TodoStatus.NEW);
        }
        if (todo.getCreatedBy() == null) {
            todo.setCreatedBy("system");
        }
        if (todo.getUpdatedBy() == null) {
            todo.setUpdatedBy("system");
        }
        if (todo.getCreatedAt() == null) {
            todo.setCreatedAt(Instant.now());
        }
        if (todo.getUpdatedAt() == null) {
            todo.setUpdatedAt(Instant.now());
        }
        return todoRepository.upsert(todo);
    }

    public void delete(TodoItem todoItem) {
        todoRepository.delete(todoItem);
    }
}
