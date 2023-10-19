package com.example.UserService.controllers;

import com.example.UserService.entity.User;
import com.example.UserService.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserAdminController {

    @Autowired
    private IUserService userService;

    @GetMapping("")
    public ResponseEntity<ArrayList<User>> getAllUser() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<User> add(@RequestBody User User){
        return new ResponseEntity<>(userService.save(User), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> edit(@PathVariable int id){
        User User = userService.getById(id).get();
        if(User != null){
            return new ResponseEntity<>(User, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        Optional<User> UserOptional = UserService.getById(id);
        return UserOptional.map(User -> new ResponseEntity<>(User, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<User> doEdit(@PathVariable("id") int id, @RequestBody User User) {
        User UserNew = userService.getById(id).get();
        if(UserNew != null){
            User.setId(UserNew.getId());
            User UserObject = userService.save(User);
            return new ResponseEntity<>(UserObject, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        User UserNew = userService.getById(id).get();
        if(UserNew != null){
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
