package com.ecommerce.project.exceptions;

import lombok.NoArgsConstructor;

public class ResourceNotFoundException extends RuntimeException {

    String resourceName;
    String field;
    String fieldName;
    Long fieldId;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String field, String fieldName, String resourceName) {
        super(String.format("%s not found with %s: %s",resourceName,field,fieldName));
        this.field = field;
        this.fieldName = fieldName;
        this.resourceName = resourceName;
    }

    public ResourceNotFoundException(String resourceName, String field, Long fieldId) {
        super(String.format("%s not found with %s: %d",resourceName,field,fieldId));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldId = fieldId;
    }
}
