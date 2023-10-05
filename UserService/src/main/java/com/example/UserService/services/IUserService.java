package com.example.UserService.services;

import com.example.UserService.entity.User;

import java.util.ArrayList;
import java.util.Optional;

public interface IUserService {
    ArrayList<User> getAll();
    Optional<User> getById(int id);
    User save(User user);
    void delete(int id);
}
