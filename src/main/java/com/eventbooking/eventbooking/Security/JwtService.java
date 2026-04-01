package com.eventbooking.eventbooking.Security;

import java.util.Date;
import org.springframework.stereotype.Service;
import com.eventbooking.eventbooking.Users.model.UserRole;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;

@Service
public class JwtService {
    
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
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
        return claims.get("role",UserRole.class);
    }

    public String getUsername(Claims claims)
    {
        return claims.getSubject();
    }

    
}
