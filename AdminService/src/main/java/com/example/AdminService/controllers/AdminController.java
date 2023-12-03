package com.example.AdminService.controllers;

import com.example.AdminService.entity.Admin;
import com.example.AdminService.services.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService AdminService;

    @GetMapping("")
    public ResponseEntity<ArrayList<Admin>> getAllAdmin() {
        return new ResponseEntity<>(AdminService.getAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Admin> add(@RequestBody Admin Admin){
        return new ResponseEntity<>(AdminService.save(Admin), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> edit(@PathVariable int id){
        Admin Admin = AdminService.getById(id).get();
        if(Admin != null){
            return new ResponseEntity<>(Admin, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<Admin> getAdmin(@PathVariable int id) {
        Optional<Admin> AdminOptional = AdminService.getById(id);
        return AdminOptional.map(Admin -> new ResponseEntity<>(Admin, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<Admin> doEdit(@PathVariable("id") int id, @RequestBody Admin Admin) {
        Admin AdminNew = AdminService.getById(id).get();
        if(AdminNew != null){
            Admin.setId(AdminNew.getId());
            Admin AdminObject = AdminService.save(Admin);
            return new ResponseEntity<>(AdminObject, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Admin> deleteAdmin(@PathVariable int id) {
        Admin AdminNew = AdminService.getById(id).get();
        if(AdminNew != null){
            AdminService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
