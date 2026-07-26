package com.eventservice.eventservice.Security;
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
    private final JwtFilter filter;

    SecurityConfig(JwtFilter filter) {
        this.filter = filter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
        
        http.csrf(csfr ->csfr.disable()).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth.requestMatchers("/user/register").permitAll().requestMatchers("/auth/login").permitAll()
        .requestMatchers("/internal/**").permitAll() //initially permitAll request . Then we can use a different
        .requestMatchers("/admin/register").permitAll()
        .requestMatchers("/admin/**").hasRole("ADMIN")
        .requestMatchers("/events/bookings").hasRole("ADMIN")
        .requestMatchers("/event/**").hasAnyRole("USER", "ADMIN","SUPERADMIN")
        .requestMatchers("/payments/*").hasAnyRole("USER", "ADMIN","SUPERADMIN").anyRequest().authenticated())
        .addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
