package com.example.CategoryService.services.impl;

import com.example.CategoryService.entity.Category;
import com.example.CategoryService.repository.ICategoryRepo;
import com.example.CategoryService.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepo CategoryRepo;

    @Override
    public ArrayList<Category> getAll() {
        ArrayList<Category> list = new ArrayList<>();
        Iterable it = CategoryRepo.findAll();
        for(Object Category: it){
            list.add((Category) Category);
        }
        return list;
    }

    @Override
    public Optional<Category> getById(int id) {
        return CategoryRepo.findById(id);
    }

    @Override
    public Category save(Category Category) {
        return CategoryRepo.save(Category);
    }

    @Override
    public void delete(int id) {
        CategoryRepo.deleteById(id);
    }
}
