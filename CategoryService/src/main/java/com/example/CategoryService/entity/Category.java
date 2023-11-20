package com.example.CategoryService.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "Category")
@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}
