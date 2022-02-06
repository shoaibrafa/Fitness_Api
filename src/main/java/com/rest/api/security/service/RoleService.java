package com.rest.api.security.service;

import com.rest.api.security.model.Role;

public interface RoleService {
    Role findByName(String name);
}
