package com.example.adsonline.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

    @GetMapping("/{entityName}/{entityId}/{fileName}")
    @ResponseBody
    public ResponseEntity<Resource> getFileByFileName(
            @Parameter(description = "Тип сущности") @PathVariable(value = "entityName") String entityName,
            @Parameter(description = "Идентификатор сущности") @PathVariable(value = "entityId") String entityId,
            @Parameter(description = "Имя файла") @PathVariable(value = "fileName") String fileName
    ) {
        return ResponseEntity.ok().body(null);
    }
}
