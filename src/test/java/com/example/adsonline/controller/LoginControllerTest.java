package com.example.adsonline.controller;

import com.example.adsonline.DTOs.LoginReqDTO;
import com.example.adsonline.DTOs.RegisterReqDTO;
import com.example.adsonline.services.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @MockBean
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    void login_ValidCredentials_ReturnsOk() throws Exception {
        LoginReqDTO req = new LoginReqDTO();
        req.setUsername("username");
        req.setPassword("password");

        when(authService.login(req.getUsername(), req.getPassword())).thenReturn(true);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(req)))
                .andExpect(status().isOk());
    }

    @Test
    void login_InvalidCredentials_ReturnsUnauthorized() throws Exception {
        LoginReqDTO req = new LoginReqDTO();
        req.setUsername("username");
        req.setPassword("password");

        when(authService.login(req.getUsername(), req.getPassword())).thenReturn(false);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(req)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void register_ValidRequest_ReturnsOk() throws Exception {
        RegisterReqDTO req = new RegisterReqDTO();
        req.setUsername("username");
        req.setPassword("password");

        when(passwordEncoder.encode(req.getPassword())).thenReturn("hashedPassword");
        when(authService.register(req)).thenReturn(true);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(req)))
                .andExpect(status().isOk());
    }

    @Test
    void register_InvalidRequest_ReturnsBadRequest() throws Exception {
        RegisterReqDTO req = new RegisterReqDTO();
        req.setUsername("username");
        req.setPassword("password");

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(req)))
                .andExpect(status().isBadRequest());
    }

    // преобразования объекта в JSON-строку
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}