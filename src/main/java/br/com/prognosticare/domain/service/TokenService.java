package br.com.prognosticare.domain.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;





@Service
public class TokenService {
    
    @Value("${TOKEN_SECRET}")
    private String secret;

    public String gerarToken(PessoaEntity pessoa){
        try {
            var algorithm = Algorithm.HMAC512(secret);
            return JWT.create()
            .withIssuer("API Prognosticare")
            .withSubject(pessoa.getEmail())
            .withExpiresAt(dataExpiracao())
            .sign(algorithm);
            
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token.", e);
        }

    }

    public String getSubject(String tokenJWT){
        try {
            var algorithm = Algorithm.HMAC512(secret);
            return JWT.require(algorithm)
            .withIssuer("API Prognosticare")
            .build()
            .verify(tokenJWT)
            .getSubject();

        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token Expirado ou inválido", e);
        }

    }


    private Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
        
    }


    
}
