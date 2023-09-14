package com.example.adsonline.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.amazonaws.services.s3.AmazonS3;
import com.example.adsonline.properties.S3Properties;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Тестирование конфигурации S3config.
 */
class S3configTest {

    /**
     * Проверяет, что метод s3 возвращает действительный экземпляр AmazonS3.
     * Для этого создается заглушка S3Properties с фиктивными значениями,
     * создается контекст приложения и запрашивается экземпляр AmazonS3.
     * После чего проверяется, что он не равен null.
     */
    @Test
    void s3_ReturnsAmazonS3Instance() {

        S3Properties mockS3Properties = mock(S3Properties.class);
        when(mockS3Properties.getAccessKey()).thenReturn("minioadmin");
        when(mockS3Properties.getSecretKey()).thenReturn("minioadmin");
        when(mockS3Properties.getUrl()).thenReturn("http://localhost/");
        when(mockS3Properties.getPort()).thenReturn(9000);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(S3config.class);
        AmazonS3 s3 = context.getBean(AmazonS3.class);

        assertNotNull(s3);

        context.close();
    }
}
