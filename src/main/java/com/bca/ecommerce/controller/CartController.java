package com.bca.ecommerce.controller;

import com.bca.ecommerce.Exception.StockException;
import com.bca.ecommerce.entity.Product;
import com.bca.ecommerce.entity.User;
import com.bca.ecommerce.repository.ProductRepository;
import com.bca.ecommerce.repository.UserRepository;
import com.bca.ecommerce.service.CartService;
import com.bca.ecommerce.service.CartServiceImpl;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private UserRepository userRepo;
    
    
    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("productId") Integer id, @RequestParam("quantity") int quantity, Principal principal,
    		RedirectAttributes redirectAttributes) {
    	
    	try {
    		Optional<User> user = userRepo.findByUsername(principal.getName());
            //Product product = productRepo.findById(id).get();
            String result = cartService.addToCart(user, Integer.valueOf(id), Integer.valueOf(quantity));
            
            redirectAttributes.addFlashAttribute("successMessage", "Product added to cart successfully!");
            
            return "redirect:/user/cart";
    	}
    	catch(StockException exception)
    	{
    		redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
    		return "redirect:/user/products";
    	}
        
    }

    @GetMapping("/cart")
    public String viewCart(Model model, Principal principal) {
        Optional<User> user = userRepo.findByUsername(principal.getName());
        model.addAttribute("cartItems", cartService.getUserCart(user.get().getUserId()));
        return "cart";
    }
    
    @PostMapping("/clear-cart")
    public String clearCart(Principal principal,
                            RedirectAttributes redirectAttributes) {

        if (principal == null) {
            return "redirect:/login";
        }

        try {
        	
        	User user = userRepo.findByUsername(principal.getName()).get();
        	
            cartService.clearCart(user);

            redirectAttributes.addFlashAttribute("success",
                    "Cart cleared successfully!");

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error",
                    "Unable to clear cart.");
        }

        return "redirect:/user/cart";
    }

}
