package com.bca.ecommerce.service;

import com.bca.ecommerce.entity.Role;
import com.bca.ecommerce.entity.User;
import com.bca.ecommerce.repository.RoleRepository;
import com.bca.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void registerUser(User user) {

        // 1. Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 2. Enable account
        user.setEnabled(true);

        // 3. Assign USER role
        Role userRole = roleRepository.findByRoleName("USER");
        user.setRoles(Collections.singleton(userRole));

        // 4. Save user
        userRepository.save(user);
    }

	@Override
	public boolean existsByUsername(String username) {
		Optional<User> u = null;
		if(username != null && !username.equalsIgnoreCase(""))
		{
			if(userRepository.findByUsername(username).isPresent())
			{
				u = userRepository.findByUsername(username);
				if(u != null && u.get().getUserId() != null && !u.isEmpty() ? true : false);
			}
		}
		else
			return false;
		
		return false;
	}
}
