package rest_api_comandas.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class UtilJwt {

    // Chave secreta fixa para gerar o token (expiração para 30 min)
    private static final String SECRET = "cienca_computacao_furb_web_2_2025.2";
    private static final long EXPIRACAO = 1000 * 60 * 30;

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Gera o token
    public String gerarToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRACAO))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Valida se o token é válido e não expirado
    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao validar token: " + e.getMessage());
            return false;
        }
    }

    // Pega o email (subject) de dentro do token
    public String getEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}

