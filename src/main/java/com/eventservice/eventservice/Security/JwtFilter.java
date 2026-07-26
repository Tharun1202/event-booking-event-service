package com.eventservice.eventservice.Security;

import java.io.IOException;
import java.util.Collections;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    final JwtService service;

    JwtFilter(JwtService service) {
        this.service = service;
    }
 
    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain) throws IOException , ServletException
    {
        String header = request.getHeader("Authorization");
        if(header == null || !header.startsWith("Bearer "))
        {
            chain.doFilter(request, response);
            return;
        }
        try{
            String token = header.substring(7);
            Claims claims = service.validateToken(token);
            String username = service.getUsername(claims);
            UserRole role = service.getRole(claims);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username,null,Collections.singletonList(()->"ROLE_" + role.name()));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        chain.doFilter(request, response);
    }
}
