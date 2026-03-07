package com.bca.ecommerce.service;

import com.bca.ecommerce.entity.User;

public interface UserService {

    void registerUser(User user);
    boolean existsByUsername(String username);
    
}
