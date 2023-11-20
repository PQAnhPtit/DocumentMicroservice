package com.example.CategoryService.services;

import com.example.CategoryService.entity.Category;

import java.util.ArrayList;
import java.util.Optional;

public interface ICategoryService {
    ArrayList<Category> getAll();
    Optional<Category> getById(int id);
    Category save(Category Exam);
    void delete(int id);
}
