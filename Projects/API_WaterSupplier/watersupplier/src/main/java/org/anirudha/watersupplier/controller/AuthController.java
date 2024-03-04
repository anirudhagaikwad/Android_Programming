package org.anirudha.watersupplier.controller;

import org.anirudha.watersupplier.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam String username, @RequestParam String password) {
        userService.createUser(username, password);
        return "User registered successfully!";
    }

    @GetMapping("/signin")
    public String signIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "Welcome, " + auth.getName() + "!";
    }
}
