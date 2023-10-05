package com.example.TextBookService.services;

import com.example.TextBookService.entity.Book;

import java.util.ArrayList;
import java.util.Optional;

public interface IBookService {
    ArrayList<Book> getAll();
    Optional<Book> getById(int id);
    Book save(Book Book);
    void delete(int id);
}
