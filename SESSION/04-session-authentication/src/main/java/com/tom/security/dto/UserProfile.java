package com.tom.security.dto;

import java.util.ArrayList;
import java.util.List;

import com.tom.bean.Role;

import lombok.Data;

@Data
public class UserProfile {
    private Long id;
    private String username;

    List<Role> roles = new ArrayList<>();
}
