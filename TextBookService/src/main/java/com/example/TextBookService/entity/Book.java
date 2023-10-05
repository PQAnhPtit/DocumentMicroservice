package com.example.TextBookService.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "Book")
@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private String link;
}
