package com.example.DocumentService.services;

import com.example.DocumentService.entity.Document;

import java.util.ArrayList;
import java.util.Optional;

public interface IDocumentService {
    ArrayList<Document> getAll();
    Optional<Document> getById(int id);
    Document save(Document Exam);
    void delete(int id);
}
