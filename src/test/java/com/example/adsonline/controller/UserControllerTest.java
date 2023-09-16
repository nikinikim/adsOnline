package com.example.adsonline.controller;

import com.example.adsonline.DTOs.NewPasswordDTO;
import com.example.adsonline.DTOs.UserDTO;
import com.example.adsonline.controllers.UserController;
import com.example.adsonline.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class UserControllerTest {

    @Test
    public void testSetPassword() {
        UserService userService = mock(UserService.class);
        UserController userController = new UserController(userService, null);

        NewPasswordDTO newPasswordDTO = new NewPasswordDTO();
        newPasswordDTO.setNewPassword("newPassword");

        Principal principal = Mockito.mock(Principal.class);

        ResponseEntity<HttpStatus> responseEntity = userController.setPassword(newPasswordDTO, principal);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Mockito.verify(userService, Mockito.times(1)).updateUserPassword(newPasswordDTO, principal);
    }

    @Test
    public void testGetUser() {
        UserService userService = mock(UserService.class);
        UserController userController = new UserController(userService, null);

        UserDTO userDTO = new UserDTO();
        ResponseEntity<UserDTO> expectedResponse = ResponseEntity.ok(userDTO);

        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(userService.getUser(principal)).thenReturn(userDTO);

        ResponseEntity<UserDTO> responseEntity = userController.getUser(principal);

        assertEquals(expectedResponse, responseEntity);
    }

    @Test
    public void testUpdateUserImage() {
        UserService userService = mock(UserService.class);
        UserController userController = new UserController(userService, null);

        MultipartFile image = new MockMultipartFile("image", new byte[]{});
        Principal principal = Mockito.mock(Principal.class);

        ResponseEntity<HttpStatus> responseEntity = userController.updateUserImage(image, principal);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Mockito.verify(userService, Mockito.times(1)).updateUserImage(principal, image);
    }

}