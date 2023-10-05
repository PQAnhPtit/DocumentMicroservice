package com.example.CommentService.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "comment")
@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int user_id;
    private String description;
}
