package com.ecommerce.project.service.impl;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private List<Category> categories = new ArrayList<Category>();
    private Long id=0L;

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        id++;
        category.setCategoryId(id);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categories.stream()
                .filter( x -> x.getCategoryId().equals(categoryId)).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found for Id : "+categoryId));

        categories.remove(category);
        return "Category Deleted Successfully for CategoryId :"+categoryId;
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {

        Optional<Category> optionalCategory = categories.stream().filter(x -> x.getCategoryId().equals(categoryId)).findFirst();

        if(optionalCategory.isPresent()){
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            return existingCategory;
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category Not Found for Id : "+categoryId);
        }
    }
}
