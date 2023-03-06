package com.hepexta.todoapp.controller;

import com.hepexta.todoapp.model.db.Category;
import com.hepexta.todoapp.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/categories")
@Validated
@Slf4j
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "Returns a Category List",
            description = "Returns a Category List without pagination")
    @ApiResponse(responseCode = "200",
            description = "Todo Category has found successfully",
            content = {@Content(mediaType = "application/json",
                        schema = @Schema(implementation = List.class))})
    @GetMapping
    public List<Category> getCategoryList() {
        return categoryService.getCategories();
    }

}
