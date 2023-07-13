package ch.zli.m223.ksh20.coworking_project.security;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthorisationAspect {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final HttpServletRequest request;

    public AuthorisationAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Around("@annotation(authorized)")
    public Object authorize(ProceedingJoinPoint joinPoint, Authorized authorized) throws Throwable {
        String[] allowedRoles = authorized.allowedRoles();

        // TODO: DRYify this
        String authToken = getAuthTokenFromRequest(request);

        if (authToken == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized");
        }

        try {
            jwtTokenProvider.validateToken(authToken);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized");
        }

        if (!isAuthorized(authToken, allowedRoles)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized");
        }

        return joinPoint.proceed();
    }

    private boolean isAuthorized(String authToken, String[] allowedRoles) {
        Map<String, ?> claims = jwtTokenProvider.getClaimsFromToken(authToken);
        String userRole = (String) claims.get("role");

        for (String allowedRole : allowedRoles) {
            if (allowedRole.equals(userRole)) {
                return true;
            }
        }
        return false;
    }

    private String getAuthTokenFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
