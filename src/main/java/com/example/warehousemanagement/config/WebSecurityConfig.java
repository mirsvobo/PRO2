package com.example.warehousemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/auth/login", "/auth/register", "/resources/**", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/items/new", "/receipts/new", "/inventories/new").hasRole("USER")
                        .requestMatchers("/items/edit/**", "/inventories/edit/**").hasRole("ADMIN")
                        .requestMatchers("/items/delete/**", "/inventories/delete/**").hasRole("ADMIN")
                        .requestMatchers("/suppliers/**", "/categories/**", "/variants/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/auth/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable()); // Pokud je potřeba CSRF ochranu deaktivovat, jinak je lepší ji mít aktivní

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
