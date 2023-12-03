package com.example.AdminService.services;


import com.example.AdminService.entity.Admin;

import java.util.ArrayList;
import java.util.Optional;

public interface IAdminService {
    ArrayList<Admin> getAll();
    Optional<Admin> getById(int id);
    Admin save(Admin Exam);
    void delete(int id);
}
