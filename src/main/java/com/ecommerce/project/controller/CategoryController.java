package com.ecommerce.project.controller;

import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/echo")
    // public ResponseEntity<String> echoMessage(@RequestParam(name = "message",defaultValue = "Default message we set in code")
    public ResponseEntity<String> echoMessage(@RequestParam(name = "message",required = false) String message){
        return new ResponseEntity<>("Echoed Message: "+message,HttpStatus.OK
        );
    }

//    @RequestMapping(value = "/public/categories", method = RequestMethod.GET) below is the same as this annotation
    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(@RequestParam(name = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
         @RequestParam(name = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize){
        CategoryResponse categories = categoryService.getAllCategories(pageNumber,pageSize);
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @PostMapping("/public/createCategory")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO,HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/deleteCategories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId){
        CategoryDTO deletedCategoryDTO = categoryService.deleteCategory(categoryId);
            return ResponseEntity.status(HttpStatus.OK).body(deletedCategoryDTO);
    }

    @PutMapping("/admin/updateCategories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO category,@PathVariable Long categoryId){
        CategoryDTO updateCategory = categoryService.updateCategory(category,categoryId);
            return ResponseEntity.status(HttpStatus.OK).body(updateCategory);

    }
}
