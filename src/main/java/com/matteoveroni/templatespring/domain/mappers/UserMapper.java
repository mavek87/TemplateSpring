package com.matteoveroni.templatespring.domain.mappers;

import com.matteoveroni.templatespring.domain.dto.AddUserDTO;
import com.matteoveroni.templatespring.domain.dto.ReadUserDTO;
import com.matteoveroni.templatespring.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    ReadUserDTO mapFromUserToReadUserDTO(User user);

    User mapFromReadUserDTOToUser(ReadUserDTO user);

    AddUserDTO mapFromUserToAddUserDTO(User user);

    User mapFromAddUserDTOToUser(AddUserDTO user);
}