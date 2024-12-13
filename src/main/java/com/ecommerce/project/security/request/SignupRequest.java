package com.ecommerce.project.security.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class SignupRequest {

    @NotBlank
    @Size(min = 3,max = 20)
    private String username;

    @NotBlank
    @Email
    @Size(max = 50)
    private String email;
    private String password;

    private Set<String> role;
}
