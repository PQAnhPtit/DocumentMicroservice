package com.example.UserService.services.impl;

import com.example.UserService.entity.User;
import com.example.UserService.repository.IUserRepo;
import com.example.UserService.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepo userRepo;

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> list = new ArrayList<>();
        Iterable it = userRepo.findAll();
        for(Object User: it){
            list.add((User) User);
        }
        return list;
    }

    @Override
    public Optional<User> getById(int id) {
        return userRepo.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public void delete(int id) {
        userRepo.deleteById(id);
    }
}
