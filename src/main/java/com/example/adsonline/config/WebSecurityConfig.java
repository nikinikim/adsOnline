package com.example.adsonline.config;
import com.example.adsonline.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


/**
 * Конфигурация безопасности приложения.
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig{
    public UserService userService;
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/swagger-ui.html/**",
            "/swagger-ui/index.html",
            "/v3/api-docs",
            "/webjars/**",
            "/login",
            "/register",
            "/",
            "/*.html",
            "/auth/**",
            "/app/**",
            "/swagger-ui.html",
            "/csrf",
            "/**/*.html",
            "/ads",
            "/images-ads/**",
            "/images-users/**"
    };

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    /**
     * Настройка провайдера аутентификации с использованием BCryptPasswordEncoder и UserService.
     *
     * @return Настроенный провайдер аутентификации.
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    /**
     * Настройка цепочки фильтров безопасности HTTP.
     *
     * @param http Объект HttpSecurity для настройки цепочки фильтров безопасности.
     * @return Объект SecurityFilterChain.
     * @throws Exception Исключение, которое может возникнуть при настройке цепочки фильтров безопасности.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .requestMatchers(AUTH_WHITELIST)
                                        .permitAll()
                                        .requestMatchers("/ads/**","/users/**","/ads/me")
                                        .authenticated())
                .cors()
                .and()
                .httpBasic(withDefaults());
        return http.build();
    }

    /**
     * Кодировщик паролей BCrypt.
     *
     * @return BCryptPasswordEncoder.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Предоставляет менеджер аутентификации для Spring Security.
     *
     * @param authenticationConfiguration Конфигурация аутентификации.
     * @return Менеджер аутентификации.
     * @throws Exception Исключение, которое может возникнуть при получении менеджера аутентификации.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
