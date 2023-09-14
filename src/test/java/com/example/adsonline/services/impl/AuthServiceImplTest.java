package com.example.adsonline.services.impl;

import com.example.adsonline.DTOs.RegisterReqDTO;
import com.example.adsonline.services.UserService;
import com.example.adsonline.services.impl.AuthServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthServiceImpl authService;

    public AuthServiceImplTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void login_ShouldReturnTrue_WhenValidCredentialsProvided() {
        // Arrange
        String userName = "testUser";
        String password = "testPassword";

        when(userService.isExist(userName)).thenReturn(true);

        UserDetails userDetails = mock(UserDetails.class);
        when(userService.loadUserByUsername(userName)).thenReturn(userDetails);

        String encryptedPassword = "encryptedPassword";
        when(userDetails.getPassword()).thenReturn(encryptedPassword);

        when(encoder.matches(password, encryptedPassword)).thenReturn(true);

        // Act
        boolean result = authService.login(userName, password);

        // Assert
        Assertions.assertTrue(result);
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void login_ShouldReturnFalse_WhenUserDoesNotExist() {
        // Arrange
        String nonExistentUserName = "nonExistentUser";
        String password = "testPassword";

        when(userService.isExist(nonExistentUserName)).thenReturn(false);

        // Act
        boolean result = authService.login(nonExistentUserName, password);

        // Assert
        Assertions.assertFalse(result);
        verify(authenticationManager, never()).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void login_ShouldReturnFalse_WhenInvalidCredentialsProvided() {
        // Arrange
        String userName = "testUser";
        String password = "testPassword";

        when(userService.isExist(userName)).thenReturn(true);

        UserDetails userDetails = mock(UserDetails.class);
        when(userService.loadUserByUsername(userName)).thenReturn(userDetails);

        String encryptedPassword = "encryptedPassword";
        when(userDetails.getPassword()).thenReturn(encryptedPassword);

        when(encoder.matches(password, encryptedPassword)).thenReturn(false);

        // Act
        boolean result = authService.login(userName, password);

        // Assert
        Assertions.assertFalse(result);
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void register_ShouldReturnTrue_WhenUserCreationSucceeds() {
        // Arrange
        RegisterReqDTO registerReqDTO = new RegisterReqDTO();

        // Act
        boolean result = authService.register(registerReqDTO);

        // Assert
        Assertions.assertTrue(result);
        verify(userService, times(1)).create(registerReqDTO);
    }
}
