package com.ecommerce.project.service.impl;

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
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
//        id++;
//        category.setCategoryId(id);
//        categories.add(category);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
//        Category category = categories.stream()
//                .filter( x -> x.getCategoryId().equals(categoryId)).findFirst()
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found for Id : "+categoryId));
//
//        categories.remove(category);
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category Not Found for Id : "+categoryId));
        categoryRepository.delete(category);
        return "Category Deleted Successfully for CategoryId :"+categoryId;
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {

//        Optional<Category> optionalCategory = categories.stream().filter(x -> x.getCategoryId().equals(categoryId)).findFirst();
//        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category Not Found for Id : "+categoryId));

//        categoryToUpdate.setCategoryName(category.getCategoryName());
        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);
        return savedCategory;


    }
}
