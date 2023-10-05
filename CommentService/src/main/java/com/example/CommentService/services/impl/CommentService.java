package com.example.CommentService.services.impl;

import com.example.CommentService.entity.Comment;
import com.example.CommentService.repository.ICommentRepo;
import com.example.CommentService.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private ICommentRepo CommentRepo;

    @Override
    public ArrayList<Comment> getAll() {
        ArrayList<Comment> list = new ArrayList<>();
        Iterable it = CommentRepo.findAll();
        for(Object Comment: it){
            list.add((Comment) Comment);
        }
        return list;
    }

    @Override
    public Optional<Comment> getById(int id) {
        return CommentRepo.findById(id);
    }

    @Override
    public Comment save(Comment Comment) {
        return CommentRepo.save(Comment);
    }

    @Override
    public void delete(int id) {
        CommentRepo.deleteById(id);
    }
}
