package com.tom.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tom.security.dto.UserProfile;
import com.tom.service.UserService;

@RestController
@RequestMapping("/profile")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<UserProfile> me() {
        return ResponseEntity.ok(null);
    }

}
