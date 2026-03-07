package com.bca.ecommerce.service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bca.ecommerce.entity.*;
import com.bca.ecommerce.repository.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(CartRepository cartRepository,
                            OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository,
                            UserRepository userRepository) {

        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
    }

    // 🔥 TRANSACTION = VERY IMPORTANT
    @Transactional
    public void checkout(Principal principal) {

    	Optional<User> user = userRepository.findByUsername(principal.getName());

        List<Cart> cartItems = cartRepository.findByUser(user.get());

        if(cartItems.isEmpty()){
            throw new RuntimeException("Cart is empty!");
        }

        double total = 0;

        // Create Order
        Order order = new Order();
        order.setUser(user.get());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("CREATED");

        orderRepository.save(order);

        // Convert Cart -> OrderItems
        for(Cart cart : cartItems){

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(cart.getProduct());
            item.setQuantity(cart.getQuantity());
            item.setPrice(cart.getProduct().getPrice());

            total += cart.getQuantity() * cart.getProduct().getPrice();

            orderItemRepository.save(item);
        }

        order.setTotalAmount(total);
        orderRepository.save(order);

        // 🔥 CLEAR CART
        cartRepository.deleteAll(cartItems);
    }

    public List<Order> getOrders(Principal principal){

    	Optional<User> user = userRepository.findByUsername(principal.getName());

        return orderRepository.findByUser(user.get());
    }
}
