package com.example.AdminService.services.impl;

import com.example.AdminService.entity.Admin;
import com.example.AdminService.repository.IAdminRepo;
import com.example.AdminService.services.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private IAdminRepo AdminRepo;

    @Override
    public ArrayList<Admin> getAll() {
        ArrayList<Admin> list = new ArrayList<>();
        Iterable it = AdminRepo.findAll();
        for(Object Admin: it){
            list.add((Admin) Admin);
        }
        return list;
    }

    @Override
    public Optional<Admin> getById(int id) {
        return AdminRepo.findById(id);
    }

    @Override
    public Admin save(Admin Admin) {
        return AdminRepo.save(Admin);
    }

    @Override
    public void delete(int id) {
        AdminRepo.deleteById(id);
    }
}
