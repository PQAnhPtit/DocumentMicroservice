package com.example.DocumentService.repository;

import com.example.DocumentService.entity.Document;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDocumentRepo extends PagingAndSortingRepository<Document, Integer> {

}
