package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.services.MyUserService;
@RestController
@RequestMapping("")
public class UserRestController {


    @Autowired
    MyUserService userService;



    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/user/add")
    public User addUser(@RequestBody User user, BindingResult result) throws Exception {
        if (result.hasErrors())
            System.err.println(result.getAllErrors());
        return userService.addUser(user);
    }

    @PostMapping("/admin/add")
    public User addAdmin(@RequestBody User user, BindingResult result) throws Exception {
        if (result.hasErrors())
            System.err.println(result.getAllErrors());
        return userService.addAdmin(user);
    }

    @DeleteMapping("/user/{id}/delete")
    public User deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
