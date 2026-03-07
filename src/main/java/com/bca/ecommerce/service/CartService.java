package com.bca.ecommerce.service;

import com.bca.ecommerce.entity.Cart;
import java.util.List;

public interface CartService {

    //void addToCart(Integer userId, Integer productId, Integer quantity);

    List<Cart> getUserCart(Integer userId);

}
