package com.example.adsonline.services.impl;

import com.example.adsonline.DTOs.NewPasswordDTO;
import com.example.adsonline.DTOs.RegisterReqDTO;
import com.example.adsonline.DTOs.UserDTO;
import com.example.adsonline.entity.Users;
import com.example.adsonline.enums.Roles;
import com.example.adsonline.exception.NotFoundInDataBaseException;
import com.example.adsonline.mappers.UserMapper;

import com.example.adsonline.repository.UserRepository;
import com.example.adsonline.services.FileService;
import com.example.adsonline.services.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Logger logger;

    @Mock
    private FileService fileService;

    @InjectMocks
    private UserServiceImpl userService;

    public UserServiceImplTest() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userMapper, userRepository, logger, fileService);
    }

    @Test
    void createUser_ShouldReturnUserDTO() {
        RegisterReqDTO registerReqDTO = new RegisterReqDTO();
        Users user = new Users();
        UserDTO userDTO = new UserDTO();

        when(userMapper.fromRegisterReqDTO(registerReqDTO)).thenReturn(user);
        when(userRepository.saveAndFlush(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDTO);

        UserDTO result = userService.create(registerReqDTO);

        assertEquals(userDTO, result);
        verify(userMapper).fromRegisterReqDTO(registerReqDTO);
        verify(userRepository).saveAndFlush(user);
        verify(userMapper).toDto(user);
    }

    @Test
    void findUserByLogin_ExistingUser_ShouldReturnUser() {
        String username = "test";
        Users user = new Users();

        when(userRepository.findUsersByUserName(username)).thenReturn(Optional.of(user));

        Users result = userService.findUserByLogin(username);

        assertEquals(user, result);
        verify(userRepository).findUsersByUserName(username);
    }

    @Test
    void findUserByLogin_NonExistingUser_ShouldThrowException() {
        String username = "nonExistingUser";

        when(userRepository.findUsersByUserName(username)).thenReturn(Optional.empty());

        assertThrows(NotFoundInDataBaseException.class, () -> {
            userService.findUserByLogin(username);
        });

        verify(userRepository).findUsersByUserName(username);
    }

    @Test
    public void testUpdateUserPassword() {
        String username = "testUser";
        String currentPassword = "oldPassword";
        String newPassword = "newPassword";

        Users userEntity = new Users();
        userEntity.setUserName(username);
        userEntity.setPassword(currentPassword);

        NewPasswordDTO newPasswordDTO = new NewPasswordDTO();
        newPasswordDTO.setCurrentPassword(currentPassword);
        newPasswordDTO.setNewPassword(newPassword);

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return username;
            }
        };

    }

    @Test
    public void testUpdateUserImage() throws IOException {
        // Подготовка данных для теста
        String username = "testUser";
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test-image.png", "image/png", "test".getBytes());

        Users userEntity = new Users();
        userEntity.setUserName(username);

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return username;
            }
        };
        String expectedImageRef = "example-image-ref";

        // Мокирование поведения репозитория и сервиса файлов
        when(userRepository.findUsersByUserName(username)).thenReturn(Optional.of(userEntity));
        when(fileService.upload(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any())).thenReturn(expectedImageRef);

        // Выполнение тестируемого метода
        userService.updateUserImage(principal, multipartFile);

        // Проверка результатов
        verify(userRepository, times(1)).findUsersByUserName(username);
        verify(fileService, times(1)).upload(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any());
        verify(userRepository, times(1)).saveAndFlush(userEntity);

        Assert.assertEquals(expectedImageRef, userEntity.getImage());
    }

    @Test
    void testGetUser_withValidPrincipal_shouldReturnUserDTO() {
        String username = "testUser";
        Principal principal = () -> username;
        Users user = new Users(username, "password", new ArrayList<>());
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUserDetails((UserDetails) user).build();

        when(userService.findUserByLogin(username)).thenReturn((Users) userDetails);
        when(userMapper.toDto((Users) userDetails)).thenReturn(new UserDTO(/* UserDTO parameters */));

        // Act
        UserDTO result = userService.getUser(principal);

        // Assert
        assertEquals(UserDTO.class, result.getClass());
        // Add additional assertions as needed

        verify(userService, times(1)).findUserByLogin(username);
        verify(userMapper, times(1)).toDto((Users) userDetails);
    }

    @Test
    void testGetUser_withInvalidPrincipal_shouldThrowException() {
        // Arrange
        Principal principal = () -> null;

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> userService.getUser(principal));
    }

    @Test
    void isExist_ShouldReturnTrue_WhenUserNameExists() {
        // Arrange
        String userName = "testUser";
        when(userRepository.existsUsersByUserName(userName)).thenReturn(true);

        // Act
        boolean result = userService.isExist(userName);

        // Assert
        Assertions.assertTrue(result);
    }

    @Test
    void isExist_ShouldReturnFalse_WhenUserNameDoesNotExist() {
        // Arrange
        String userName = "nonExistentUser";
        when(userRepository.existsUsersByUserName(userName)).thenReturn(false);

        // Act
        boolean result = userService.isExist(userName);

        // Assert
        Assertions.assertFalse(result);
    }

    @Test
    void updateUser_ShouldReturnUpdatedUserDTO_WhenPrincipalExists() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setPhone("1234567890");

        String principalName = "loggedInUser";
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn(principalName);

        Users user = new Users();
        user.setUserName(principalName);

        when(userRepository.findUsersByUserName(principalName)).thenReturn(Optional.of(user));
        when(userRepository.saveAndFlush(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDTO);

        // Act
        UserDTO result = userService.updateUser(userDTO, principal);

        // Assert
        Assertions.assertEquals(userDTO, result);
        Assertions.assertEquals(userDTO.getFirstName(), user.getFirstName());
        Assertions.assertEquals(userDTO.getLastName(), user.getLastName());
        Assertions.assertEquals(userDTO.getPhone(), user.getPhone());
        verify(userRepository, times(1)).saveAndFlush(user);
    }

    @Test
    void loadUserByUsername_ShouldReturnUserDetails_WhenUserNameExists() {
        // Arrange
        String userName = "testUser";
        Users user = new Users();
        user.setUserName(userName);
        user.setPassword("password");
        user.setRole(Roles.USER);

        when(userRepository.findUsersByUserName(userName)).thenReturn(Optional.of(user));

        // Act
        UserDetails userDetails = userService.loadUserByUsername(userName);

        // Assert
        Assertions.assertEquals(userName, userDetails.getUsername());
        Assertions.assertEquals(user.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(1, userDetails.getAuthorities().size());
        Assertions.assertEquals(user.getRole().toString(), userDetails.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    void loadUserByUsername_ShouldThrowUsernameNotFoundException_WhenUserNameDoesNotExist() {
        // Arrange
        String nonExistentUserName = "nonExistentUser";
        when(userRepository.findUsersByUserName(nonExistentUserName)).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(nonExistentUserName));
    }
}


