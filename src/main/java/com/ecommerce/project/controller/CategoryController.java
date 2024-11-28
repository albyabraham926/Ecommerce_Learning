package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
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

//    @RequestMapping(value = "/public/categories", method = RequestMethod.GET) below is the same as this annotation
    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @PostMapping("/public/createCategory")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category Created Successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/deleteCategories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        try {
            String status = categoryService.deleteCategory(categoryId);
//            return new ResponseEntity<>(status, HttpStatus.OK); // valid
            return ResponseEntity.status(HttpStatus.OK).body(status);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @PutMapping("/admin/updateCategories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category,@PathVariable Long categoryId){
        try{
            Category updateCategory = categoryService.updateCategory(category,categoryId);
            return ResponseEntity.status(HttpStatus.OK).body("Category Updated for the id : "+categoryId);
        } catch (ResponseStatusException e) {
        return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

}
