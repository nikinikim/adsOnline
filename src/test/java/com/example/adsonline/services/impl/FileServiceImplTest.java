package com.example.adsonline.services.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.example.adsonline.exception.BusinessLogicException;
import com.example.adsonline.services.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("FileServiceImpl tests")
public class FileServiceImplTest {

    private static final String ENTITY_NAME = "Entity";
    private static final String ENTITY_ID = "1";
    private static final String FILE_NAME = "file.txt";
    private static final byte[] FILE_CONTENT = "Hello World!".getBytes();

    private FileService fileService;

    @Mock
    private AmazonS3 s3;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        fileService = new FileServiceImpl(s3);
    }

    @Test
    @DisplayName("upload should create bucket if it doesn't exist")
    void testUploadCreateBucket() {
        String bucketName = ENTITY_NAME.toLowerCase();
        when(s3.doesBucketExistV2(bucketName)).thenReturn(false);

        fileService.upload(ENTITY_NAME, ENTITY_ID, FILE_NAME, FILE_CONTENT);

        verify(s3).createBucket(bucketName);
        verify(s3).putObject(any(PutObjectRequest.class));
    }

    @Test
    @DisplayName("upload should not create bucket if it already exists")
    void testUploadNoCreateBucket() {
        String bucketName = ENTITY_NAME.toLowerCase();
        when(s3.doesBucketExistV2(bucketName)).thenReturn(true);

        fileService.upload(ENTITY_NAME, ENTITY_ID, FILE_NAME, FILE_CONTENT);

        verify(s3, never()).createBucket(bucketName);
        verify(s3).putObject(any(PutObjectRequest.class));
    }

    @Test
    @DisplayName("getFile should return file content if it exists")
    void testGetFileExists() {
        String bucketName = ENTITY_NAME.toLowerCase();
        S3Object s3Object = mock(S3Object.class);
        S3ObjectInputStream inputStream = mock(S3ObjectInputStream.class);
        when(s3.getObject(bucketName, ENTITY_ID + "/" + FILE_NAME)).thenReturn(s3Object);
        when(s3Object.getObjectContent()).thenReturn(inputStream);
        try {
            when(inputStream.readAllBytes()).thenReturn(FILE_CONTENT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        byte[] result = fileService.getFile(ENTITY_NAME, ENTITY_ID, FILE_NAME);

        assertArrayEquals(FILE_CONTENT, result);
    }

    @Test
    @DisplayName("getFile should throw exception if file doesn't exist")
    void testGetFileNotExists() {
        String bucketName = ENTITY_NAME.toLowerCase();
        when(s3.getObject(bucketName, ENTITY_ID + "/" + FILE_NAME)).thenThrow(AmazonS3Exception.class);

        assertThrows(BusinessLogicException.class, () -> fileService.getFile(ENTITY_NAME, ENTITY_ID, FILE_NAME));
    }

    @Test
    @DisplayName("checkImageExtensionFile should return true for image files")
    void testCheckImageExtensionFileTrue() {
        assertTrue(fileService.checkImageExtensionFile("image.jpg"));
        assertTrue(fileService.checkImageExtensionFile("IMAGE.png"));
        assertTrue(fileService.checkImageExtensionFile("logo.svg"));
        assertTrue(fileService.checkImageExtensionFile("icon.ico"));
    }

    @Test
    @DisplayName("checkImageExtensionFile should return false for non-image files")
    void testCheckImageExtensionFileFalse() {
        assertFalse(fileService.checkImageExtensionFile("document.pdf"));
        assertFalse(fileService.checkImageExtensionFile("code.java"));
        assertFalse(fileService.checkImageExtensionFile("audio.mp3"));
    }

    @Test
    @DisplayName("getExtension should return file extension")
    void testGetExtension() {
        assertEquals("txt", fileService.getExtension(FILE_NAME));
    }
}