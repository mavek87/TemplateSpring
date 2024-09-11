package com.matteoveroni.templatespring.services;

import com.matteoveroni.templatespring.domain.dto.AddUserDTO;
import com.matteoveroni.templatespring.domain.dto.ReadUserDTO;
import com.matteoveroni.templatespring.domain.mappers.UserMapper;
import com.matteoveroni.templatespring.domain.model.User;
import com.matteoveroni.templatespring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<ReadUserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::mapFromUserToReadUserDTO)
                .collect(Collectors.toList());
    }

    public AddUserDTO addUser(AddUserDTO addUserDTO) {
        User user = userMapper.mapFromAddUserDTOToUser(addUserDTO);
        userRepository.save(user);
        return addUserDTO;
    }
}
