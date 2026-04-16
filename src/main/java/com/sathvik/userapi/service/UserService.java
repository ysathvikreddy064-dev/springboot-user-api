package com.sathvik.userapi.service;

import com.sathvik.userapi.dto.UserRequest;
import com.sathvik.userapi.exception.UserNotFoundException;
import com.sathvik.userapi.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final Map<UUID, User> store = new LinkedHashMap<>();

    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    public User findById(UUID id) {
        return Optional.ofNullable(store.get(id))
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User create(UserRequest request) {
        UUID id = UUID.randomUUID();
        User user = new User(id, request.getName(), request.getEmail());
        store.put(id, user);
        return user;
    }
}