package com.ecommerce.project.model;

import jakarta.persistence.*;

//@Entity
@Entity(name = "categories") // name field will help to map with the class with the mentioned Table name , otherwise simple class name will be the Tbale name by default
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // this will automatic generate unique id value for this column when new record inserted, no need to maintain manual
    private Long categoryId;

//    @Column(name="Name")
    private String categoryName;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Category() {
    }
}
