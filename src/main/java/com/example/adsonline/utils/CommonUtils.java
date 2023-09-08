package com.example.adsonline.utils;

import com.example.adsonline.exception.BusinessLogicException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.io.IOException;

@UtilityClass
public class CommonUtils {

    public <T> T objectFromString(String jsonString, Class<T> type) {
        T obj;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            obj = objectMapper.readValue(jsonString, type);
        } catch (JsonProcessingException e) {
            throw new BusinessLogicException(
                    String.format("Предоставленный параметр должен иметь формат типа %s", type.getName()), HttpStatus.BAD_REQUEST);
        }
        return obj;
    }

    public <T> String objectToString(T obj) {
        String jsonString;

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writer().withDefaultPrettyPrinter();
        try {
            jsonString = objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            throw new BusinessLogicException(
                    String.format("Не удалось преобразовать объект %s в строку", obj));
        }
        return jsonString;
    }
}
