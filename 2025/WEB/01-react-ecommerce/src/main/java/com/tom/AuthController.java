package com.tom;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest, HttpSession session) {
        try {
            String username = loginRequest.get("username");
            String password = loginRequest.get("password");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            // 驗證成功，將使用者資訊存入 Session
            session.setAttribute("user", authentication.getPrincipal());
            
            // 將 Authentication 存入 SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            // 驗證失敗
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid username or password");
            return ResponseEntity.status(401).body(errorResponse);
        }
    }


    @GetMapping("/protected-resource")
    public ResponseEntity<String> getProtectedResource(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        return ResponseEntity.ok("This is a protected resource");
    }
}
