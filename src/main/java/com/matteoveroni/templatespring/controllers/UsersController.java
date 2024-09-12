package com.matteoveroni.templatespring.controllers;

import com.matteoveroni.templatespring.domain.dto.ListResult;
import com.matteoveroni.templatespring.domain.dto.ReadUserDTO;
import com.matteoveroni.templatespring.domain.dto.WriteUserDTO;
import com.matteoveroni.templatespring.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api/users", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping
    public ListResult<ReadUserDTO> getAll() {
        return new ListResult<>(userService.readAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadUserDTO> get(@PathVariable(name = "id") Long id) {
        ReadUserDTO user = userService
                .readUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found with id " + id));
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<ReadUserDTO> create(@RequestBody WriteUserDTO user) {
        ReadUserDTO createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReadUserDTO> update(@PathVariable(name = "id") Long id, @RequestBody WriteUserDTO user) {
        ReadUserDTO updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
    }
}
