package ch.zli.m223.ksh20.coworking_project.security;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Component;

import ch.zli.m223.ksh20.coworking_project.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

    // @Value("${jwt.secret}")
    private String jwtSecret;

    // @Value("${jwt.expiration}")
    private int jwtExpirationInMs = 30000;

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

    // genereate secret key
    public JwtTokenProvider() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[64];
        random.nextBytes(bytes);

        String secret = Base64.getEncoder().encodeToString(bytes);
        this.jwtSecret = secret;
    }
}
