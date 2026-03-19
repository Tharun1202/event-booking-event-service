package com.eventbooking.eventbooking.Security;

import java.util.Date;

import com.eventbooking.eventbooking.Users.model.UserRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;

public class JwtService {
    
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public String generateJWTToken(String userid ,String username ,UserRole role)
    {
       String token =  Jwts.builder().subject(username).claim("role",role.name()).claim("userid",userid).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+
    3600000)).signWith(key).compact();
        return token;
    }

    public static String validateToken(String token)
    {
       return ""; 

    }
}
