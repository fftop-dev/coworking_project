package ch.zli.m223.ksh20.coworking_project.security;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import ch.zli.m223.ksh20.coworking_project.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

    private String jwtSecret;

    private int jwtExpirationInMs = 60000;

    public String generateToken(User user) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(user.getUuid())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public void validateToken(String authToken) {
        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
    }

    public Map<String, ?> getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    public JwtTokenProvider() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[64];
        random.nextBytes(bytes);

        String secret = Base64.getEncoder().encodeToString(bytes);
        this.jwtSecret = secret;
    }
}
