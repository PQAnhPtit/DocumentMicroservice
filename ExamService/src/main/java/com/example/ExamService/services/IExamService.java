package com.example.ExamService.services;

import com.example.ExamService.entity.Exam;

import java.util.ArrayList;
import java.util.Optional;

public interface IExamService {
    ArrayList<Exam> getAll();
    Optional<Exam> getById(int id);
    Exam save(Exam Exam);
    void delete(int id);
}
