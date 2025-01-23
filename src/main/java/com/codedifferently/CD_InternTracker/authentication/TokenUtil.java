package com.codedifferently.CD_InternTracker.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class TokenUtil {
    //will change these two to environment variables before deployment
    private static final String SECRET_KEY = "placeholder";
    private static final long EXPIRATION_TIME = 86400000;

    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    //combines the secret key and username to generate a new unique key
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    public long getExpirationTime () {
        return EXPIRATION_TIME;
    }
    //takes the output of generateToken and finds the username that it was generated with
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
    public boolean validateToken(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }



}
