package com.bca.ecommerce.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bca.ecommerce.entity.LoginAttempt;

public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Integer> {

	long countByUsernameAndSuccessFalseAndAttemptTimeAfter(
            String username,
            LocalDateTime time
    );
}
