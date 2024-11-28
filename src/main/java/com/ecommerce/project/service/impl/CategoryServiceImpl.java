package com.ecommerce.project.service.impl;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;
import com.ecommerce.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

//    private List<Category> categories = new ArrayList<Category>();
//    private Long id=0L;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty())
            throw new APIException("No Category is Present in the List As of Now");
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
//        id++;
//        category.setCategoryId(id);
//        categories.add(category);
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory != null)
             throw new APIException("Category with Name "+category.getCategoryName()+" already exists.");
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
//        Category category = categories.stream()
//                .filter( x -> x.getCategoryId().equals(categoryId)).findFirst()
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found for Id : "+categoryId));
//
//        categories.remove(category);
//        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category Not Found for Id : "+categoryId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","CategoryId",categoryId));

        categoryRepository.delete(category);
        return "Category Deleted Successfully for CategoryId :"+categoryId;
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","CategoryId",categoryId));

        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);
        return savedCategory;


    }
}
