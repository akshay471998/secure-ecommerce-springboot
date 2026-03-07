package com.bca.ecommerce.controller;

import com.bca.ecommerce.entity.User;
import com.bca.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    /**
     * Show Registration Page
     */
//    @GetMapping("/showRegister")
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("user", new User());
//        return "register";   // register.jsp
//    }

    /**
     * Process Registration Form
     */
    @PostMapping("/perform_register")
    public String registerUser(@ModelAttribute("user") User user,
                               Model model) {

        // Check if username already exists
        if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }

        // Save user (password encoding + role assignment happens in service)
        userService.registerUser(user);

        // Redirect to login after successful registration
        return "redirect:/login?registered=true";
    }
}
