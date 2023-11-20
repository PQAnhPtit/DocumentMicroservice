package com.example.DocumentService.services.impl;

import com.example.DocumentService.entity.CategoryDocument;
import com.example.DocumentService.repository.ICategoryDocumentRepo;
import com.example.DocumentService.services.ICategoryDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CategoryDocumentService implements ICategoryDocumentService {

    @Autowired
    private ICategoryDocumentRepo CategoryDocumentRepo;

    @Override
    public ArrayList<CategoryDocument> getAll() {
        ArrayList<CategoryDocument> list = new ArrayList<>();
        Iterable it = CategoryDocumentRepo.findAll();
        for(Object CategoryDocument: it){
            list.add((CategoryDocument) CategoryDocument);
        }
        return list;
    }

    @Override
    public Optional<CategoryDocument> getById(int id) {
        return CategoryDocumentRepo.findById(id);
    }

    @Override
    public CategoryDocument save(CategoryDocument Category_Document) {
        return CategoryDocumentRepo.save(Category_Document);
    }

    @Override
    public void delete(int id) {
        CategoryDocumentRepo.deleteById(id);
    }
}
