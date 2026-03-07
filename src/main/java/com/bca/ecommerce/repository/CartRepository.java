package com.bca.ecommerce.repository;

import com.bca.ecommerce.entity.Cart;
import com.bca.ecommerce.entity.Product;
import com.bca.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByUser(User user);

    Optional<Cart> findByUserAndProduct(User user, Product product);

    void deleteByUser(User user);
}
