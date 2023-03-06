package com.hepexta.todoapp.controller;

import com.hepexta.todoapp.model.db.TodoItem;
import com.hepexta.todoapp.model.db.TodoStatus;
import com.hepexta.todoapp.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/todos")
@Validated
@Slf4j
@AllArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @Operation(summary = "Returns a Todo List",
            description = "Returns a Todo List without pagination")
    @ApiResponse(responseCode = "200",
            description = "Todo list has found successfully",
            content = {@Content(mediaType = "application/json",
                        schema = @Schema(implementation = List.class))})
    @GetMapping
    public List<TodoItem> getTodoList() {
        return todoService.getTodos();
    }

    @GetMapping("/{id}")
    public TodoItem findById(@PathVariable String id) {
        return todoService.findById(UUID.fromString(id));
    }

    @Operation(summary = "Creates a Todo Item",
            description = "Creates a Todo Item and returns it")
    @ApiResponse(responseCode = "200",
            description = "Todo has been successfully created",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = TodoItem.class))})
    @PostMapping
    public ResponseEntity<TodoItem> createTodo(@RequestBody TodoItem todo) {
        log.info("Create Todo started: {}", todo);
        TodoItem createdTodo = todoService.upsert(todo);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTodo.getTodoItemId())
                .toUri();

        return ResponseEntity.created(uri).body(createdTodo);
    }

    @PutMapping("/{id}")
    public TodoItem updateTodo(@PathVariable String id, @RequestBody TodoItem todo) {
        log.info("Update Todo started: {}", todo);
        todo.setTodoItemId(UUID.fromString(id));
        return todoService.upsert(todo);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable String id) {
        todoService.delete(TodoItem.builder().todoItemId(UUID.fromString(id)).build());
    }
}
