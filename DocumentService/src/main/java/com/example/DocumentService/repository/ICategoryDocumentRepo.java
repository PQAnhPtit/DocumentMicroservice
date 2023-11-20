package com.example.DocumentService.repository;

import com.example.DocumentService.entity.CategoryDocument;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryDocumentRepo extends PagingAndSortingRepository<CategoryDocument, Integer> {

}
