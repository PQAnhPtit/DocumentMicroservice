package com.example.CommentService.controllers;

import com.example.CommentService.entity.Comment;
import com.example.CommentService.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService CommentService;

    @GetMapping("")
    public ResponseEntity<ArrayList<Comment>> getAllComment() {
        return new ResponseEntity<>(CommentService.getAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Comment> add(@RequestBody Comment Comment){
        return new ResponseEntity<>(CommentService.save(Comment), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> edit(@PathVariable int id){
        Comment Comment = CommentService.getById(id).get();
        if(Comment != null){
            return new ResponseEntity<>(Comment, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable int id) {
        Optional<Comment> CommentOptional = CommentService.getById(id);
        return CommentOptional.map(Comment -> new ResponseEntity<>(Comment, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<Comment> doEdit(@PathVariable("id") int id, @RequestBody Comment Comment) {
        Comment CommentNew = CommentService.getById(id).get();
        if(CommentNew != null){
            Comment.setId(CommentNew.getId());
            Comment CommentObject = CommentService.save(Comment);
            return new ResponseEntity<>(CommentObject, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable int id) {
        Comment CommentNew = CommentService.getById(id).get();
        if(CommentNew != null){
            CommentService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
