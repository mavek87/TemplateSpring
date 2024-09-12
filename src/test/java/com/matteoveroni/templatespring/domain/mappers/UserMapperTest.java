package com.matteoveroni.templatespring.domain.mappers;

import com.matteoveroni.templatespring.domain.dto.ReadUserDTO;
import com.matteoveroni.templatespring.domain.dto.WriteUserDTO;
import com.matteoveroni.templatespring.domain.entity.User;
import com.matteoveroni.templatespring.utils.ObjectComparator;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserMapperTest {

    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = Mappers.getMapper(UserMapper.class);
    }

    @Test
    void test_map_from_User_to_ReadUserDTO() throws IllegalAccessException {
        // Arrange
        User user = new User(1L, "john_doe", 30);
        user.setId(1L);
        user.setUsername("john_doe");
        user.setAge(30);

        // Act
        ReadUserDTO readUserDTO = userMapper.mapFromUserToReadUserDTO(user);

        // Assert
        assertTrue(ObjectComparator.areFieldsEqual(user, readUserDTO), "User has not the same fields of ReadUserDTO");
    }

    @Test
    void test_map_from_ReadUserDTO_to_User() throws IllegalAccessException {
        // Arrange
        ReadUserDTO readUserDTO = new ReadUserDTO(1L, "john_doe", 30);

        // Act
        User user = userMapper.mapFromReadUserDTOToUser(readUserDTO);

        // Assert
        assertTrue(ObjectComparator.areFieldsEqual(user, readUserDTO), "User has not the same fields of ReadUserDTO");
    }

    @Test
    void test_map_from_User_to_WriteUserDTO() throws IllegalAccessException {
        // Arrange
        User user = new User(null, "john_doe", 30);

        // Act
        WriteUserDTO writeUserDTO = userMapper.mapFromUserToWriteUserDTO(user);

        // Assert
        assertTrue(ObjectComparator.areFieldsEqualExcludingSome(writeUserDTO, user, Set.of("id")), "WriteUserDTO has not the same fields of User");
    }

    @Test
    void test_map_from_WriteUserDTO_to_User() throws IllegalAccessException {
        // Arrange
        WriteUserDTO writeUserDTO = new WriteUserDTO("john_doe", 30);

        // Act
        User user = userMapper.mapFromWriteUserDTOToUser(writeUserDTO);

        // Assert
        assertTrue(ObjectComparator.areFieldsEqualExcludingSome(user, writeUserDTO, Set.of("id")), "User has not the same fields of WriteUserDTO");
        assertNull(user.getId(), "User ID should be null when mapped from WriteUserDTO");
    }
}
