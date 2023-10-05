package com.example.TextBookService.services.impl;

import com.example.TextBookService.entity.Book;
import com.example.TextBookService.repository.IBookRepo;
import com.example.TextBookService.services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    private IBookRepo BookRepo;

    @Override
    public ArrayList<Book> getAll() {
        ArrayList<Book> list = new ArrayList<>();
        Iterable it = BookRepo.findAll();
        for(Object Book: it){
            list.add((Book) Book);
        }
        return list;
    }

    @Override
    public Optional<Book> getById(int id) {
        return BookRepo.findById(id);
    }

    @Override
    public Book save(Book Book) {
        return BookRepo.save(Book);
    }

    @Override
    public void delete(int id) {
        BookRepo.deleteById(id);
    }
}
