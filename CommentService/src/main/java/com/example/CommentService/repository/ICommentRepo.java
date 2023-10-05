package com.example.CommentService.repository;

import com.example.CommentService.entity.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepo extends PagingAndSortingRepository<Comment, Integer> {

}
