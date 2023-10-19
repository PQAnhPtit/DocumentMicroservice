package com.example.DocumentService.repositories;

import com.example.DocumentService.entities.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepo extends CrudRepository<Document,Integer> {

}
