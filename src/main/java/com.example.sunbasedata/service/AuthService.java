package com.example.sunbasedata.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthService {
    private final Map<String, String> userCredentials = new HashMap<>();
    private final Map<String, String> userTokens = new HashMap<>();

    public AuthService() {
        // Initialize some test users with their credentials
        userCredentials.put("test@sunbasedata.com", "Test@123");
    }

    public String authenticateUser(String loginId, String password) {
        if (userCredentials.containsKey(loginId) && userCredentials.get(loginId).equals(password)) {
            String token = generateToken();
            userTokens.put(loginId, token);
            return token;
        }
        return null; // Authentication failed
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }
}
