package br.com.e_commerce.demo.service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.e_commerce.demo.domain.usuario.Usuario;

@Service
public class TokenService {

    public String gerarToken(Usuario usuario) throws Exception {
        try {
            Algorithm algorithm = Algorithm.HMAC256("12345678");
            return JWT.create()
                    .withIssuer("e-commerce")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(expiracao(3))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new Exception("Erro ao gerar token JWT de acesso!");
        }
    }

    public String verificarToken(String token) throws IOException {
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256("12345678");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("e-commerce")
                    .build();

            decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception) {
            throw new IOException("Erro ao verificar token JWT de acesso!");
        }
    }

    private Instant expiracao(Integer horas) {
        return LocalDateTime.now().plusHours(horas).toInstant(ZoneOffset.of("-03:00"));
    }
}
