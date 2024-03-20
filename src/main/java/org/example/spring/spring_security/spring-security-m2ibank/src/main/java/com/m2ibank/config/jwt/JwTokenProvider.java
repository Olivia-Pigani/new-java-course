package com.m2ibank.config.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;

@Component
public class JwTokenProvider {


   private final JwtAuthentificationEntryPoint jwtAuthentificationEntryPoint;

    private String secret;

    @Autowired
    public JwTokenProvider(JwtAuthentificationEntryPoint jwtAuthentificationEntryPoint) {
        this.jwtAuthentificationEntryPoint = jwtAuthentificationEntryPoint;
    }

    private SecretKey getSignInKey(){
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }





}
