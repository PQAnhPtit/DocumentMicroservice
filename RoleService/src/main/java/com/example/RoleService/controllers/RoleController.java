package com.example.RoleService.controllers;

import com.example.RoleService.entity.Role;
import com.example.RoleService.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService RoleService;

    @GetMapping("")
    public ResponseEntity<ArrayList<Role>> getAllRole() {
        return new ResponseEntity<>(RoleService.getAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Role> add(@RequestBody Role Role){
        return new ResponseEntity<>(RoleService.save(Role), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> edit(@PathVariable int id){
        Role Role = RoleService.getById(id).get();
        if(Role != null){
            return new ResponseEntity<>(Role, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<Role> getRole(@PathVariable int id) {
        Optional<Role> RoleOptional = RoleService.getById(id);
        return RoleOptional.map(Role -> new ResponseEntity<>(Role, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<Role> doEdit(@PathVariable("id") int id, @RequestBody Role Role) {
        Role RoleNew = RoleService.getById(id).get();
        if(RoleNew != null){
            Role.setId(RoleNew.getId());
            Role RoleObject = RoleService.save(Role);
            return new ResponseEntity<>(RoleObject, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Role> deleteRole(@PathVariable int id) {
        Role RoleNew = RoleService.getById(id).get();
        if(RoleNew != null){
            RoleService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
