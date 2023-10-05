package com.example.ExamService.controllers;

import com.example.ExamService.entity.Exam;
import com.example.ExamService.services.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private IExamService ExamService;

    @GetMapping("")
    public ResponseEntity<ArrayList<Exam>> getAllExam() {
        return new ResponseEntity<>(ExamService.getAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Exam> add(@RequestBody Exam Exam){
        return new ResponseEntity<>(ExamService.save(Exam), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> edit(@PathVariable int id){
        Exam Exam = ExamService.getById(id).get();
        if(Exam != null){
            return new ResponseEntity<>(Exam, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<Exam> getExam(@PathVariable int id) {
        Optional<Exam> ExamOptional = ExamService.getById(id);
        return ExamOptional.map(Exam -> new ResponseEntity<>(Exam, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<Exam> doEdit(@PathVariable("id") int id, @RequestBody Exam Exam) {
        Exam ExamNew = ExamService.getById(id).get();
        if(ExamNew != null){
            Exam.setId(ExamNew.getId());
            Exam ExamObject = ExamService.save(Exam);
            return new ResponseEntity<>(ExamObject, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Exam> deleteExam(@PathVariable int id) {
        Exam ExamNew = ExamService.getById(id).get();
        if(ExamNew != null){
            ExamService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
