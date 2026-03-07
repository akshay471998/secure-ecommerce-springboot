package com.bca.ecommerce.repository;

import com.bca.ecommerce.entity.Order;
import com.bca.ecommerce.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	List<Order> findByUser(User user);
	
}
		