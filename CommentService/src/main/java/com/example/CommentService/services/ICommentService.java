package com.example.CommentService.services;

import com.example.CommentService.entity.Comment;

import java.util.ArrayList;
import java.util.Optional;

public interface ICommentService {
    ArrayList<Comment> getAll();
    Optional<Comment> getById(int id);
    Comment save(Comment Comment);
    void delete(int id);
}
