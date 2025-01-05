package com.ecommerce.project.service;

import com.ecommerce.project.payload.CartDTO;

public interface CartService {
    CartDTO addProductToCard(Long productId, Integer quantity);
}
