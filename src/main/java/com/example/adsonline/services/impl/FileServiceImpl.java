package com.example.adsonline.services.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.example.adsonline.exception.BusinessLogicException;
import com.example.adsonline.services.FileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final AmazonS3 s3;
    public static List<String> imageExtension = Arrays.asList("jpg", "png", "jpeg", "svg", "bmp", "ico");

    @Override
    public String upload(String entityName, String entityId, String fileName, byte[] file) {
        String bucket = StringUtils.lowerCase(entityName);
        if (!this.s3.doesBucketExistV2(bucket)) {
            this.s3.createBucket(bucket);
        }
        InputStream inputStream = new ByteArrayInputStream(file);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, String.format("%s/%s",entityId,fileName), inputStream, null);
        this.s3.putObject(putObjectRequest);
        return String.format("/%s/%s/%s",bucket,entityId,fileName);
    }

    @Override
    public byte[] getFile(String entityName, String entityId, String fileName) {
        String bucketName = StringUtils.lowerCase(entityName);
        try (S3Object obj = s3.getObject(bucketName, String.format("%s/%s", entityId, fileName))) {
            return this.readBytes(obj);
        } catch (AmazonS3Exception | IOException e) {
            throw new BusinessLogicException("Не удалось найти файл " + fileName, HttpStatus.NOT_FOUND);
        }
    }

    private byte[] readBytes(S3Object obj) {
        S3ObjectInputStream stream = obj.getObjectContent();
        try {
            return IOUtils.toByteArray(stream);
        } catch (IOException e) {
            throw new BusinessLogicException("Не удалось прочитать файл " + obj.getKey(), HttpStatus.CONFLICT);
        }
    }
    public boolean checkImageExtensionFile(String fileName) {
        String extension = getExtension(fileName).toLowerCase();
        Optional<String> foundExtension = imageExtension.stream().filter(ext -> ext.equals(extension)).findAny();
        return foundExtension.isPresent();
    }

    public String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
