package com.rest.api.security.service;

import java.util.List;

import com.rest.api.security.model.User;
import com.rest.api.security.model.UserDto;

public interface UserService {
    User save(UserDto user);
    List<User> findAll();
    User findOne(String username);
}
