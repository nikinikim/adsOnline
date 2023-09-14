package com.example.adsonline.config;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Тестирование конфигурации SwaggerUiConfiguration.
 */
class SwaggerUiConfigurationTest {

    /**
     * Проверяет, что конфигурация SwaggerUiConfiguration правильно регистрирует бины.
     * Создается контекст приложения, регистрируется конфигурация SwaggerUiConfiguration,
     * и затем проверяется, что бины были зарегистрированы правильно.
     */
    @Test
    void testSwaggerUiConfiguration() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setServletContext(new MockServletContext());
        context.register(SwaggerUiConfiguration.class);
        context.refresh();

        assertNotNull(context.getBean(SwaggerUiConfiguration.class));
        assertNotNull(context.getBean(WebMvcConfigurer.class));

        context.close();
    }
}