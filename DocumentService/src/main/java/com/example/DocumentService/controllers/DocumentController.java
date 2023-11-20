package com.example.DocumentService.controllers;

import com.example.DocumentService.entity.Document;
import com.example.DocumentService.services.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private IDocumentService documentService;

    @GetMapping("")
    public ResponseEntity<ArrayList<Document>> getAllDocument() {
        return new ResponseEntity<>(documentService.getAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Document> add(@RequestBody Document Document){
        return new ResponseEntity<>(documentService.save(Document), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> edit(@PathVariable int id){
        Document Document = documentService.getById(id).get();
        if(Document != null){
            return new ResponseEntity<>(Document, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<Document> getDocument(@PathVariable int id) {
        Optional<Document> DocumentOptional = DocumentService.getById(id);
        return DocumentOptional.map(Document -> new ResponseEntity<>(Document, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<Document> doEdit(@PathVariable("id") int id, @RequestBody Document Document) {
        Document DocumentNew = documentService.getById(id).get();
        if(DocumentNew != null){
            Document.setId(DocumentNew.getId());
            Document DocumentObject = documentService.save(Document);
            return new ResponseEntity<>(DocumentObject, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Document> deleteDocument(@PathVariable int id) {
        Document DocumentNew = documentService.getById(id).get();
        if(DocumentNew != null){
            documentService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
