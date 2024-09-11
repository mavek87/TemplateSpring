package com.matteoveroni.templatespring.controllers;

import com.matteoveroni.templatespring.domain.dto.ReadUserDTO;
import com.matteoveroni.templatespring.domain.dto.WriteUserDTO;
import com.matteoveroni.templatespring.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/{id}")
    public ReadUserDTO getUserById(@PathVariable(name = "id") Long id) {
        return userService
                .readUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found with id " + id));
    }

    @PostMapping
    public ReadUserDTO updateUser(@RequestBody WriteUserDTO user) {
        return userService.writeUser(user);
    }
}
