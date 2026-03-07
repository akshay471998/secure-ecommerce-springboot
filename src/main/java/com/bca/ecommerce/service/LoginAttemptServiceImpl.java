package com.bca.ecommerce.service;

import com.bca.ecommerce.entity.LoginAttempt;
import com.bca.ecommerce.repository.LoginAttemptRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginAttemptServiceImpl implements LoginAttemptService {

    private static final int MAX_ATTEMPTS = 3;
    private static final int LOCK_TIME_MINUTES = 15;

    @Autowired
    private LoginAttemptRepository repository;

    @Override
    public void loginSucceeded(String username) {
        LoginAttempt attempt = new LoginAttempt();
        attempt.setUsername(username);
        attempt.setSuccess(true);
        repository.save(attempt);
    }

    @Override
    public void loginFailed(String username) {
        LoginAttempt attempt = new LoginAttempt();
        attempt.setUsername(username);
        attempt.setSuccess(false);
        repository.save(attempt);
    }

    @Override
    public boolean isBlocked(String username) {
        LocalDateTime cutoffTime = LocalDateTime.now().minusMinutes(LOCK_TIME_MINUTES);
        long attempts = repository.countByUsernameAndSuccessFalseAndAttemptTimeAfter(
                username, cutoffTime
        );
        return attempts >= MAX_ATTEMPTS;
    }
}
