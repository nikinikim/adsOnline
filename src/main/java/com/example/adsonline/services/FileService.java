package com.example.adsonline.services;

import org.springframework.core.io.Resource;

import java.util.Map;

public interface FileService {
    String upload(String entityName,
                       String entityId,
                       String fileName,
                       String contentType,
                       Map<String, String> metadata,
                       byte[] file);
    Resource getFile(String entityName,
                     String entityId,
                     String fileName);
}
