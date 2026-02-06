package com.devnexus.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable()) 
            
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                
                // 1. Permitir Login y Registro
                .requestMatchers("/api/usuarios/login", "/api/usuarios/registro", "/api/usuarios/google").permitAll()
                
                // 2. Permitir VER listas (GET)
                .requestMatchers(HttpMethod.GET, "/api/usuarios/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/proyectos/**").permitAll()

                // 3. 🔥 LO NUEVO: Permitir EDITAR usuarios y proyectos (PUT)
                .requestMatchers(HttpMethod.PUT, "/api/usuarios/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/proyectos/**").permitAll()
                
                // 4. Permitir Swagger
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                
                // El resto (como BORRAR) sigue pidiendo Token
                .anyRequest().authenticated() 
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(
            "http://localhost:4200", 
            "https://portafolio-calle-torres-2025.web.app"
        )); 
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type")); // Headers importantes
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}