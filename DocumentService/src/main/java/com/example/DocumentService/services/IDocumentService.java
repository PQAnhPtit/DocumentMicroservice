package com.example.DocumentService.services;

import com.example.DocumentService.entities.Document;
import com.example.DocumentService.models.GoogleDriveFileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface IDocumentService {
    void saveFile(MultipartFile file, String name, String description);
    void saveFileLink(GoogleDriveFileDTO file, String name, String description);
    Document getFileById(int id);
    ArrayList<Document> getFiles();
    void deleteById(int id);
}
