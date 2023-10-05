package com.example.ExamService.repository;

import com.example.ExamService.entity.Exam;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExamRepo extends PagingAndSortingRepository<Exam, Integer> {

}
