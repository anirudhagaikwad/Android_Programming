package org.anirudha.watersupplier.tokenfilter;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class AuthTokenFilter extends GenericFilterBean {

    private final TokenProvider tokenProvider;

    public AuthTokenFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            // Extract token from the request
            String token = extractToken(request);

            // Validate and authenticate the token
            if (token != null && tokenProvider.validateToken(token)) {
                Authentication authentication = tokenProvider.getAuthentication(token);

                // Set authentication in SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Error occurred while processing authentication token", e);
        }

        // Continue with the filter chain
        chain.doFilter(request, response);
    }

    private String extractToken(ServletRequest request) {
        // Implement token extraction logic from the request
        // For example, extract from the Authorization header or request parameters
        // This is a basic example, and you may need to customize it based on your needs
        // Ensure that you handle different token types and sources according to your application
        // For simplicity, let's assume it's in the Authorization header as a Bearer token

        // Example: Authorization: Bearer your_token
        String headerValue = ((HttpServletRequest) request).getHeader(HttpHeaders.AUTHORIZATION);

        if (headerValue != null && headerValue.startsWith("Bearer ")) {
            return headerValue.substring(7);
        }

        return null;
    }
}
