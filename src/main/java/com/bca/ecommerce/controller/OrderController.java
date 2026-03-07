package com.bca.ecommerce.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bca.ecommerce.service.OrderService;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/user/checkout")
    public String checkout(Principal principal,
                           RedirectAttributes ra){

        try{
            orderService.checkout(principal);
            ra.addFlashAttribute("success","Order placed successfully!");
        }
        catch(Exception e){
            ra.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/user/orders";
    }

    @GetMapping("/user/orders")
    public String orders(org.springframework.ui.Model model,
                         Principal principal){

        model.addAttribute("orders",
                orderService.getOrders(principal));

        return "orders";
    }
}
