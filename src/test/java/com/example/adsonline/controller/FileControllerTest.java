package com.example.adsonline.controller;

import com.example.adsonline.services.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileService fileService;

    @Test
    void getFileByFileName_ReturnsFile() throws Exception {
        String entityName = "someEntity";
        String entityId = "123";
        String fileName = "example.txt";
        byte[] fileContent = "Hello, World!".getBytes();
        when(fileService.getFile(entityName, entityId, fileName)).thenReturn(fileContent);

        mockMvc.perform(get("/{entityName}/{entityId}/{fileName}", entityName, entityId, fileName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_OCTET_STREAM))
                .andExpect(content().bytes(fileContent));
    }
}
