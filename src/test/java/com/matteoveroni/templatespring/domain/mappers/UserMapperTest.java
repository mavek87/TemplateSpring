package com.matteoveroni.templatespring.domain.mappers;

import com.matteoveroni.templatespring.domain.dto.ReadUserDTO;
import com.matteoveroni.templatespring.domain.dto.WriteUserDTO;
import com.matteoveroni.templatespring.domain.model.User;
import com.matteoveroni.templatespring.utils.ObjectComparator;
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
    void test_map_from_User_to_ReadUserDTO() {
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
    void test_map_from_ReadUserDTO_to_User() {
        // Arrange
        ReadUserDTO readUserDTO = new ReadUserDTO(1L, "john_doe", 30);

        // Act
        User user = userMapper.mapFromReadUserDTOToUser(readUserDTO);

        // Assert
        assertTrue(ObjectComparator.areFieldsEqual(user, readUserDTO), "User has not the same fields of ReadUserDTO");
    }

    @Test
    void test_map_from_User_to_WriteUserDTO() {
        // Arrange
        User user = new User(null, "john_doe", 30);

        // Act
        WriteUserDTO writeUserDTO = userMapper.mapFromUserToWriteUserDTO(user);

        // Assert
        assertEquals(user.getUsername(), writeUserDTO.username(), "Username should match in WriteUserDTO");
        assertEquals(user.getAge(), writeUserDTO.age(), "Age should match in WriteUserDTO");
    }

    @Test
    void test_map_from_WriteUserDTO_to_User() {
        // Arrange
        WriteUserDTO writeUserDTO = new WriteUserDTO("john_doe", 30);

        // Act
        User user = userMapper.mapFromWriteUserDTOToUser(writeUserDTO);

        // Assert
        assertEquals(writeUserDTO.username(), user.getUsername(), "WriteUserDTO username should match User username");
        assertEquals(writeUserDTO.age(), user.getAge(), "WriteUserDTO age should match User age");
        assertNull(user.getId(), "User ID should be null when mapped from WriteUserDTO");
    }
}
