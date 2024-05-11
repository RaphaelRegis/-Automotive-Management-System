package com.sistemaAutomotivo.SistemaAutomotivo.modulos.seguranca.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.seguranca.entities.Usuario;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
    
            return JWT.create()
                    .withIssuer("spripe")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
            
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro na hora de criar o token", e);
        }




    }

    public String validarToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
    
            return JWT.require(algorithm)
                    .withIssuer("spripe")
                    .build()
                    .verify(token)
                    .getSubject();
            
        } catch (JWTVerificationException e) {
            System.out.println(token);
			System.out.println(e.getMessage());
			return "";
        }
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
