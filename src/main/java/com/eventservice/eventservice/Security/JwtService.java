package com.eventservice.eventservice.Security;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import java.nio.charset.StandardCharsets;
import java.security.Key;

@Service
public class JwtService {
    
    private final Key key;

    public JwtService(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    
    public String generateJWTToken(String userid ,String username ,UserRole role)
    {
       String token =  Jwts.builder().setSubject(username).claim("role",role.name()).claim("userid",userid).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+
    3600000)).signWith(key).compact();
        return token;
    }

    public Claims validateToken(String token)
    {
       Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        return claims;
    }
    

    public String getUserId(Claims claims)
    {
        return claims.get("userid",String.class);
    }

    public UserRole getRole(Claims claims)
    {
        String role = claims.get("role",String.class);
        return UserRole.valueOf(role);
    }

    public String getUsername(Claims claims)
    {
        return claims.getSubject();
    }

    
}
