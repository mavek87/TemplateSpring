package com.matteoveroni.templatespring.services;

import com.matteoveroni.templatespring.domain.dto.AddUserDTO;
import com.matteoveroni.templatespring.domain.dto.ReadUserDTO;
import com.matteoveroni.templatespring.domain.model.User;
import com.matteoveroni.templatespring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<ReadUserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new ReadUserDTO(user.getId(), user.getUsername(), user.getAge()))
                .collect(Collectors.toList());
    }

    public AddUserDTO addUser(AddUserDTO addUserDTO) {
        User user = new User();
        user.setUsername(addUserDTO.username());
        user.setAge(addUserDTO.age());
        userRepository.save(user);
        return addUserDTO;
    }
}
