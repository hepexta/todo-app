package com.hepexta.todoapp.model.db;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TodoItem {
    private UUID todoItemId;
    private String label;
    private String description;
    private UUID categoryId;
    private String categoryName;
    private TodoStatus status;
    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
}
