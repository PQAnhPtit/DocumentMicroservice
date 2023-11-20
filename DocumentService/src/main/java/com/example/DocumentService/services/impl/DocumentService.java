package com.example.DocumentService.services.impl;

import com.example.DocumentService.entity.Document;
import com.example.DocumentService.repository.IDocumentRepo;
import com.example.DocumentService.services.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DocumentService implements IDocumentService {

    @Autowired
    private IDocumentRepo DocumentRepo;

    @Override
    public ArrayList<Document> getAll() {
        ArrayList<Document> list = new ArrayList<>();
        Iterable it = DocumentRepo.findAll();
        for(Object Document: it){
            list.add((Document) Document);
        }
        return list;
    }

    @Override
    public Optional<Document> getById(int id) {
        return DocumentRepo.findById(id);
    }

    @Override
    public Document save(Document Document) {
        return DocumentRepo.save(Document);
    }

    @Override
    public void delete(int id) {
        DocumentRepo.deleteById(id);
    }
}
