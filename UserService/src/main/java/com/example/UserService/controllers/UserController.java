package com.example.UserService.controllers;

import com.example.UserService.entity.User;
import com.example.UserService.repository.IUserRepo;
import com.example.UserService.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/loginDV")
    public String loginGG(HttpServletResponse response) {
        DefaultOidcUser user = (DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        /*Cookie userCookie = new Cookie("user", user.getEmail());
        userCookie.setMaxAge(3600);
        response.addCookie(userCookie);*/
        return user.getEmail();
    }

    @GetMapping("/login-user/{email}/{password}")
    public User login(@PathVariable String email, @PathVariable String password, HttpServletResponse response) {
        User user = userService.login(email, password);
        /*Cookie userCookie = new Cookie("user", user.getId()+"");
        userCookie.setMaxAge(3600);
        response.addCookie(userCookie);*/
        return user;
    }

    @GetMapping("/register")
    public User register(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String cookieUser = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    String cookieValue = cookie.getValue();
                    cookieUser += cookieValue;
                    Cookie cookieNew = new Cookie("user", null);
                    cookieNew.setMaxAge(0);
                    response.addCookie(cookieNew);
                    break;
                }
            }
        }
        return cookieUser;
    }

    @PutMapping("/edit-profile/{id}")
    public ResponseEntity<User> doEdit(@PathVariable("id") int id, @RequestBody User user) {
        User userNew = userService.getById(id).get();
        if(userNew != null){
            user.setId(userNew.getId());
            User UserObject = userService.save(user);
            return new ResponseEntity<>(UserObject, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
