package com.example.DocumentService.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "Document")
@Entity
@Data
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private String link;
    private String img;
}
