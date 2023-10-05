package com.example.TextBookService.controllers;

import com.example.TextBookService.entity.Book;
import com.example.TextBookService.services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookService Bookervice;

    @GetMapping("")
    public ResponseEntity<ArrayList<Book>> getAllBook() {
        return new ResponseEntity<>(Bookervice.getAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Book> add(@RequestBody Book Book){
        return new ResponseEntity<>(Bookervice.save(Book), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> edit(@PathVariable int id){
        Book Book = Bookervice.getById(id).get();
        if(Book != null){
            return new ResponseEntity<>(Book, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable int id) {
        Optional<Book> BookOptional = Bookervice.getById(id);
        return BookOptional.map(Book -> new ResponseEntity<>(Book, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<Book> doEdit(@PathVariable("id") int id, @RequestBody Book Book) {
        Book BookNew = Bookervice.getById(id).get();
        if(BookNew != null){
            Book.setId(BookNew.getId());
            Book BookObject = Bookervice.save(Book);
            return new ResponseEntity<>(BookObject, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable int id) {
        Book BookNew = Bookervice.getById(id).get();
        if(BookNew != null){
            Bookervice.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
