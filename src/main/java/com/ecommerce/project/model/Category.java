package com.ecommerce.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Entity(name = "categories") // name field will help to map with the class with the mentioned Table name , otherwise simple class name will be the Tbale name by default
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // this will automatic generate unique id value for this column when new record inserted, no need to maintain manual
    private Long categoryId;

//    @Column(name="Name")
    private String categoryName;

}
