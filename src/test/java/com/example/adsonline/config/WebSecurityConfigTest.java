package com.example.adsonline.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестирование конфигурации WebSecurityConfig.
 */
@SpringBootTest
class WebSecurityConfigTest {

    @Autowired
    private WebSecurityConfig webSecurityConfig;

    /**
     * Проверяет, что DaoAuthenticationProvider не является null.
     */
    @Test
    void daoAuthenticationProviderNotNull() {
        assertNotNull(webSecurityConfig.daoAuthenticationProvider());
    }

    /**
     * Проверяет, что SecurityFilterChain не является null.
     */
    @Test
    void securityFilterChainNotNull() throws Exception {
        assertNotNull(webSecurityConfig.filterChain(null));
    }

    /**
     * Проверяет, что BCryptPasswordEncoder не является null.
     */
    @Test
    void passwordEncoderNotNull() {
        assertNotNull(webSecurityConfig.passwordEncoder());
    }

    /**
     * Проверяет, что AuthenticationManager не является null.
     */
    @Test
    void authenticationManagerNotNull() throws Exception {
        assertNotNull(webSecurityConfig.authenticationManager(null));
    }
}