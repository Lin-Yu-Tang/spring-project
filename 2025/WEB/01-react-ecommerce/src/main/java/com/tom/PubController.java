package com.tom;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PubController {

    @GetMapping("/check-session")
    public ResponseEntity<String> checkSession(HttpSession session) {
        // 確保 Session 存在
        if (session == null || session.getAttribute("user") == null) {
            return ResponseEntity.status(403).body("Session invalid");
        }
        return ResponseEntity.ok("Session valid");
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello, this is a public endpoint!");
    }

}
