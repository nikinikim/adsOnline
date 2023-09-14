package com.example.adsonline.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Конфигурация для Swagger UI, которая добавляет обработчики ресурсов и контроллеры представлений.
 */
@Configuration
public class SwaggerUiConfiguration implements WebMvcConfigurer {

    /**
     * Добавляет обработчики ресурсов для Swagger UI.
     *
     * @param registry Реестр обработчиков ресурсов.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.
                addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
    }

    /**
     * Добавляет контроллер представления для перенаправления на Swagger UI.
     *
     * @param registry Реестр контроллеров представлений.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/swagger-ui/").setViewName("forward:/swagger-ui/index.html");
    }

}
