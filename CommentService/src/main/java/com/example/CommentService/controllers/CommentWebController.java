package com.example.CommentService.controllers;

import com.example.CommentService.entity.Comment;
import com.example.CommentService.services.ICommentService;
import com.example.CommentService.services.impl.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CommentWebController {

    @Autowired
    private ICommentService commentService;

    /*@GetMapping("/post/{user_id}/{catedoc_id}")
    public Comment getComment(@PathVariable int user_id, @PathVariable int catedoc_id) {
        Comment comment = new Comment();
        comment.setUser_id(user_id);
        comment.setCatedoc_id(catedoc_id);
    }*/
}
