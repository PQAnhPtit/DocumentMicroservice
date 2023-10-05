package com.example.RoleService.services;

import com.example.RoleService.entity.Role;

import java.util.ArrayList;
import java.util.Optional;

public interface IRoleService {
    ArrayList<Role> getAll();
    Optional<Role> getById(int id);
    Role save(Role Role);
    void delete(int id);
}
