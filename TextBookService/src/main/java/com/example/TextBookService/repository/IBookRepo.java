package com.example.TextBookService.repository;

import com.example.TextBookService.entity.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepo extends PagingAndSortingRepository<Book, Integer> {

}
