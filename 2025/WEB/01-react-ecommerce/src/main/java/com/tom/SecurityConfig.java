package com.tom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {}) // 啟用 CORS
                .csrf(csrf -> csrf.disable()) // 禁用 CSRF，因為前端使用的是 REST API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/login", "/public/**", "/public/check-session").permitAll() // 公開路徑
//                        .requestMatchers().authenticated() // 已登录用户可访问
                        .anyRequest().authenticated() // 其他路徑需要驗證
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"))
                        .logoutSuccessUrl("/public/logout-success")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173")); // 允許的來源
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // 允許的 HTTP 方法
        configuration.setAllowedHeaders(List.of("*")); // 允許的請求頭
        configuration.setAllowCredentials(true); // 允許攜帶憑證（如 Cookie）

        org.springframework.web.cors.UrlBasedCorsConfigurationSource source =
                new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 配置應用於所有路徑
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        return new InMemoryUserDetailsManager(
                org.springframework.security.core.userdetails.User.withUsername("user")
                        .password(passwordEncoder.encode("password"))
                        .roles("USER")
                        .build(),
                org.springframework.security.core.userdetails.User.withUsername("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles("ADMIN")
                        .build()
        );
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
