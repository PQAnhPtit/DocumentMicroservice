package com.example.CategoryService.controllers;

import com.example.CategoryService.entity.Category;
import com.example.CategoryService.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService CategoryService;

    @GetMapping("")
    public ResponseEntity<ArrayList<Category>> getAllCategory() {
        return new ResponseEntity<>(CategoryService.getAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Category> add(@RequestBody Category Category){
        return new ResponseEntity<>(CategoryService.save(Category), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> edit(@PathVariable int id){
        Category Category = CategoryService.getById(id).get();
        if(Category != null){
            return new ResponseEntity<>(Category, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable int id) {
        Optional<Category> CategoryOptional = CategoryService.getById(id);
        return CategoryOptional.map(Category -> new ResponseEntity<>(Category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<Category> doEdit(@PathVariable("id") int id, @RequestBody Category Category) {
        Category CategoryNew = CategoryService.getById(id).get();
        if(CategoryNew != null){
            Category.setId(CategoryNew.getId());
            Category CategoryObject = CategoryService.save(Category);
            return new ResponseEntity<>(CategoryObject, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable int id) {
        Category CategoryNew = CategoryService.getById(id).get();
        if(CategoryNew != null){
            CategoryService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
