package com.example.DocumentService.services;

import com.example.DocumentService.entity.CategoryDocument;

import java.util.ArrayList;
import java.util.Optional;

public interface ICategoryDocumentService {
    ArrayList<CategoryDocument> getAll();
    Optional<CategoryDocument> getById(int id);
    CategoryDocument save(CategoryDocument category_document);
    void delete(int id);
}
