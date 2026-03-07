package com.bca.ecommerce.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bca.ecommerce.entity.Product;
import com.bca.ecommerce.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;

	@GetMapping("/user/products")
	public String viewProducts(Model model, Principal principal) {
	    model.addAttribute("products", productService.getAllProducts());
	    model.addAttribute("username", principal.getName().toString());
	    return "products";
	}

    @GetMapping("/admin/product")	//Protected by Spring Security role "Admin".
    public String addProductPage(Principal principal) {
    	if(principal.getName().equalsIgnoreCase("ADMIN"))
    		return "add-product";
    	else
    		return "error";
    }
    
    @PostMapping("/admin/add-product")	//Protected by Spring Security role "Admin".
    public String addProductProcess(Product product, Principal principal) {
    	if(principal.getName().equalsIgnoreCase("ADMIN"))
    	{
    		productService.saveProduct(product);
    		return "redirect:/dashboard?showSuccessMessage=true";
    	}
    	else
    		return "redirect:/dashboard?showSuccessMessage=false";
    }
}
