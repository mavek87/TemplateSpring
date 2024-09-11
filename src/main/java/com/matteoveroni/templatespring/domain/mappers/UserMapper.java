package com.matteoveroni.templatespring.domain.mappers;

import com.matteoveroni.templatespring.domain.dto.WriteUserDTO;
import com.matteoveroni.templatespring.domain.dto.ReadUserDTO;
import com.matteoveroni.templatespring.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    ReadUserDTO mapFromUserToReadUserDTO(User user);

    User mapFromReadUserDTOToUser(ReadUserDTO user);

    WriteUserDTO mapFromUserToAddUserDTO(User user);

    User mapFromAddUserDTOToUser(WriteUserDTO user);
}