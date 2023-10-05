package com.example.RoleService.services.impl;

import com.example.RoleService.entity.Role;
import com.example.RoleService.repository.IRoleRepo;
import com.example.RoleService.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepo RoleRepo;

    @Override
    public ArrayList<Role> getAll() {
        ArrayList<Role> list = new ArrayList<>();
        Iterable it = RoleRepo.findAll();
        for(Object Role: it){
            list.add((Role) Role);
        }
        return list;
    }

    @Override
    public Optional<Role> getById(int id) {
        return RoleRepo.findById(id);
    }

    @Override
    public Role save(Role Role) {
        return RoleRepo.save(Role);
    }

    @Override
    public void delete(int id) {
        RoleRepo.deleteById(id);
    }
}
