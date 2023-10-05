package com.example.ZuulServer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/home")
    public String index() {
        return "<h1>gsgvgsg</h1>";
    }

}