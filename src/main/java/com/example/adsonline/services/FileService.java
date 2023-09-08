package com.example.adsonline.services;

public interface FileService {
    String upload(String entityName,
                       String entityId,
                       String fileName,
                       byte[] file);
    byte[] getFile(String entityName,
                   String entityId,
                   String fileName);

    boolean checkImageExtensionFile(String fileName);
    String getExtension(String fileName);
}
