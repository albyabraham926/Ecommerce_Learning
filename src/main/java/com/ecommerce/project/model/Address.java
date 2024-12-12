package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min = 5,message = "Street Name must be atLeast 5 characters")
    private String street;

    @NotBlank
    @Size(min = 5,message = "Building Name must be atLeast 5 characters")
    private String buildingName;

    @NotBlank
    @Size(min = 4,message = "City Name must be atLeast 4 characters")
    private String city;

    @NotBlank
    @Size(min = 2,message = "State Name must be atLeast 2 characters")
    private String state;

    @NotBlank
    @Size(min = 2,message = "Country Name must be atLeast 2 characters")
    private String country;

    @NotBlank
    @Size(min = 6,message = "Pincode must be atLeast 6 characters")
    private String pincode;

    @ToString.Exclude // this filed excluded from toString method
    @ManyToMany(mappedBy = "addresses") // mappedby name is the column name of the User table which maps with adress with the colun name as addresses
    private List<User> users = new ArrayList<>();

    public Address(String street, String buildingName, String city, String state, String country, String pincode) {
        this.street = street;
        this.buildingName = buildingName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }
}
