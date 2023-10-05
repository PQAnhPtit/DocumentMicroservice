package com.example.ExamService.services.impl;

import com.example.ExamService.entity.Exam;
import com.example.ExamService.repository.IExamRepo;
import com.example.ExamService.services.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ExamService implements IExamService {

    @Autowired
    private IExamRepo ExamRepo;

    @Override
    public ArrayList<Exam> getAll() {
        ArrayList<Exam> list = new ArrayList<>();
        Iterable it = ExamRepo.findAll();
        for(Object Exam: it){
            list.add((Exam) Exam);
        }
        return list;
    }

    @Override
    public Optional<Exam> getById(int id) {
        return ExamRepo.findById(id);
    }

    @Override
    public Exam save(Exam Exam) {
        return ExamRepo.save(Exam);
    }

    @Override
    public void delete(int id) {
        ExamRepo.deleteById(id);
    }
}
