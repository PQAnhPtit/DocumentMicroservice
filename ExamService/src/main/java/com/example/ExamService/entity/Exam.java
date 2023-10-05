package com.example.ExamService.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "Exam")
@Entity
@Data
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private String link;
}
