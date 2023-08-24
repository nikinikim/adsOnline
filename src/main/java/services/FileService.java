package services;

<<<<<<< HEAD:src/main/java/com/example/adsonline/services/FileService.java
package com.example.adsonline.services;
=======
package services;
>>>>>>> Kristina_feature_23.08:src/main/java/services/FileService.java

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
