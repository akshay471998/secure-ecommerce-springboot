package com.bca.ecommerce.security;

import com.bca.ecommerce.service.LoginAttemptService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler
        implements AuthenticationFailureHandler {

    @Autowired
    private LoginAttemptService attemptService;

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception)
            throws IOException, ServletException {

        String username = request.getParameter("username");
     // Record failed attempt
        attemptService.loginFailed(username);

        if (exception instanceof LockedException) {
            response.sendRedirect("/login?error=locked");
        } else if (exception instanceof BadCredentialsException) {
            response.sendRedirect("/login?error=invalid");
        } else {
            response.sendRedirect("/login?error=true");	
        }
    }
}
