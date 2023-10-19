package com.example.DocumentService.services.impl;

import com.example.DocumentService.entities.Document;
import com.example.DocumentService.models.GoogleDriveFileDTO;
import com.example.DocumentService.repositories.DocumentRepo;
import com.example.DocumentService.services.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Service
public class DocumentService implements IDocumentService {

    @Autowired
    DocumentRepo documentRepo;

    @Override
    public void saveFile(MultipartFile file, String name, String description) {
        String docname = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            Document doc = new Document();
            doc.setDocument_name(name);
            doc.setDescription(description);
            doc.setDocument_type(file.getContentType());
            doc.setLink(null);
            doc.setDocName(docname);
            documentRepo.save(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveFileLink(GoogleDriveFileDTO file, String name, String description) {
        try {
            Document doc = new Document();
            doc.setDocument_name(name);
            doc.setDescription(description);
            doc.setDocument_type("Document from Google Drive");
            doc.setLink(file.getLink());
            documentRepo.save(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Document getFileById(int id) {
        return documentRepo.findById(id).get();
    }

    @Override
    public ArrayList<Document> getFiles() {
        ArrayList<Document> list = new ArrayList<>();
        Iterable it = documentRepo.findAll();
        for (Object doc: it){
            list.add((Document) doc);
        }
        return list;
    }

    @Override
    public void deleteById(int id){
        documentRepo.deleteById(id);
    }
}
