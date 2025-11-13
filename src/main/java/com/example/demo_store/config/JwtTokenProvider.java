package com.example.demo_store.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String secretKey;

    @Value("${app.jwt.expiration}")
    private Long jwtExpiration;

    private Algorithm algorithm;

    @PostConstruct
    protected void init() {
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return JWT.create()
                .withSubject(authentication.getName())
                .withClaim("roles", authorities)
                .withIssuedAt(now)
                .withExpiresAt(now.plus(jwtExpiration, ChronoUnit.MINUTES))
                .sign(algorithm);
    }

    public String getUsernameFromToken(String token) {
        DecodedJWT jwt = JWT.require(algorithm)
                .build()
                .verify(token);
        return jwt.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(algorithm)
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}