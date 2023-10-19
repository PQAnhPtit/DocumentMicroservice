package com.example.UserService.controllers;

import com.example.UserService.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class HomeController {
    @GetMapping("/loginGG")
    public String index() {
        return "bdbbbdfbdfb";
    }
}
