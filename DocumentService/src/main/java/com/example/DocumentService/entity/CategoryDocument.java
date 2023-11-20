package com.example.DocumentService.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "Category_Document")
@Entity
@Data
public class CategoryDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int document_id;
    private int category_id;
    private int user_id;
}
