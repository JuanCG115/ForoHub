package com.alurachallenge.ForoHub.security;

import com.alurachallenge.ForoHub.domain.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("ForoHub")
                    .withSubject(usuario.getCorreoElectronico())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al generar el token jwt", exception);
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            return null;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.require(algorithm)
                    .withIssuer("ForoHub")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            // En lugar de lanzar RuntimeException, podrías retornar null para que el filtro no muera
            return null;
        }
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
