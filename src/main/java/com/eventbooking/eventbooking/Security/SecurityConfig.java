package com.eventbooking.eventbooking.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration 
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
        http.csrf(csfr ->csfr.disable()).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll().requestMatchers("/user/login").permitAll()
        .requestMatchers("/admin/events/**").hasRole("ADMIN")
        .requestMatchers("/admin/create").hasRole("ADMIN")
        .requestMatchers("/events/bookings").hasRole("ADMIN")
        .requestMatchers("/events/**").hasAnyRole("USER", "ADMIN","SUPERADMIN")
        .requestMatchers("/payments/**").hasAnyRole("USER", "ADMIN","SUPERADMIN").anyRequest().authenticated())
        .addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
