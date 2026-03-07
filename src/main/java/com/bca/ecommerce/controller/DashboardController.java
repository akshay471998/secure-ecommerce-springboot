package com.bca.ecommerce.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model , Principal principal) {
    	
    	model.addAttribute("username", principal.getName().toString());
    	model.addAttribute("role", (principal.getName().equalsIgnoreCase("ADMIN")) ? "ADMIN" : "USER");
        return "dashboard";
    }
}
