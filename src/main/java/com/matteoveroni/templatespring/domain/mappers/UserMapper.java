package com.matteoveroni.templatespring.domain.mappers;

import com.matteoveroni.templatespring.domain.dto.ReadUserDTO;
import com.matteoveroni.templatespring.domain.dto.WriteUserDTO;
import com.matteoveroni.templatespring.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    ReadUserDTO mapFromUserToReadUserDTO(User user);

    User mapFromReadUserDTOToUser(ReadUserDTO user);

    WriteUserDTO mapFromUserToWriteUserDTO(User user);

    @Mapping(target = "id", ignore = true)
    User mapFromWriteUserDTOToUser(WriteUserDTO user);
}