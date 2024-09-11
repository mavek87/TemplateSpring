package com.matteoveroni.templatespring.controllers;

import com.matteoveroni.templatespring.domain.dto.WriteUserDTO;
import com.matteoveroni.templatespring.domain.dto.ReadUserDTO;
import com.matteoveroni.templatespring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<ReadUserDTO> getAllUsers() {
        return userService.readAllUsers();
    }

    @PostMapping
    public WriteUserDTO updateUser(@RequestBody WriteUserDTO user) {
        return userService.writeUser(user);
    }
}
