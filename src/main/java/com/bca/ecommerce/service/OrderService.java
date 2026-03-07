package com.bca.ecommerce.service;

import java.security.Principal;
import java.util.List;

import com.bca.ecommerce.entity.Order;

public interface OrderService {
	void checkout(Principal principal);

    List<Order> getOrders(Principal principal);
}
