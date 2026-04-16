package com.sathvik.userapi.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final Map<String, String> users = new HashMap<>();

    @GetMapping
    public Map<String, String> getAll() {
        return users;
    }

    @PostMapping
    public String create(@RequestBody String name) {
        String id = UUID.randomUUID().toString();
        users.put(id, name);
        return id;
    }
}