package com.matteoveroni.templatespring.services;

import com.matteoveroni.templatespring.domain.dto.WriteUserDTO;
import com.matteoveroni.templatespring.domain.dto.ReadUserDTO;
import com.matteoveroni.templatespring.domain.mappers.UserMapper;
import com.matteoveroni.templatespring.domain.model.User;
import com.matteoveroni.templatespring.repositories.UserRepository;
import java.util.Optional;
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

    public List<ReadUserDTO> readAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapFromUserToReadUserDTO)
                .collect(Collectors.toList());
    }

    public Optional<ReadUserDTO> readUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            ReadUserDTO readUserDTO = userMapper.mapFromUserToReadUserDTO(user.get());
            return Optional.of(readUserDTO);
        } else {
            return Optional.empty();
        }
    }

    public ReadUserDTO createUser(WriteUserDTO writeUserDTO) {
        User user = userMapper.mapFromWriteUserDTOToUser(writeUserDTO);
        userRepository.save(user);
        return userMapper.mapFromUserToReadUserDTO(user);
    }

    public ReadUserDTO updateUser(Long id, WriteUserDTO writeUserDTO) {
        User user = userMapper.mapFromWriteUserDTOToUser(writeUserDTO);
        user.setId(id);
        userRepository.save(user);
        return userMapper.mapFromUserToReadUserDTO(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
