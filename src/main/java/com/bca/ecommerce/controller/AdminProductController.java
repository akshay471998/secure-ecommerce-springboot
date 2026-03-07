package com.bca.ecommerce.controller;

import com.bca.ecommerce.entity.Product;
import com.bca.ecommerce.service.ProductService;
import com.bca.ecommerce.service.ProductServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductServiceImpl impl;
    
//    @GetMapping("/add-product")
//    public String showAddProductForm(Model model) {
//        model.addAttribute("product", new Product());
//        return "add-product";
//    }

    @PostMapping("/admin/save-product")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/admin/add-product?success";
    }
    
    @PostMapping("/delete-product")
    public String deleteProduct(@RequestParam Integer productId,
                                RedirectAttributes ra){
    	try 
    	{
    		impl.deleteProduct(productId);

            ra.addFlashAttribute("success",
                    "Product deleted successfully!");
    	}
    	catch(Exception e){
    	    ra.addFlashAttribute("error",
    	        "Product cannot be deleted because it exists in orders.");
    	}

        return "redirect:/admin/products";
    }
    
    @GetMapping("/products")
    public String adminProducts(Model model){
        model.addAttribute("products", productService.getAllProducts());
        return "admin-products";
    }
    
    @GetMapping("/edit-product/{id}")
    public String editProductPage(@PathVariable("id") Integer id,
                                  Model model) {

        Product product = productService.findById(id);

        model.addAttribute("product", product);

        return "admin-edit-product";
    }
    
    @PostMapping("/update-product")
    public String updateProduct(@ModelAttribute Product product,
                                RedirectAttributes ra) {

        productService.updateProduct(product);

        ra.addFlashAttribute("success",
                "Product updated successfully!");

        return "redirect:/admin/products";
    }


}
