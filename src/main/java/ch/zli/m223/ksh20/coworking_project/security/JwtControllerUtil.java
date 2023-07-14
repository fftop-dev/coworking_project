package ch.zli.m223.ksh20.coworking_project.security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtControllerUtil {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String getAuthTokenFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (var cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public Map<String, ?> getClaimsFromRequest(HttpServletRequest request) {
        String tokenCookie = getAuthTokenFromRequest(request);
        if (tokenCookie == null)
            return null;
        return getClaimsFromToken(tokenCookie);
    }

    public Map<String, ?> getClaimsFromToken(String tokenCookie) {
        return jwtTokenProvider.getClaimsFromToken(tokenCookie);
    }

    public String getRoleFromToken(String tokenCookie) {
        Map<String, ?> claims = getClaimsFromToken(tokenCookie);
        return (String) claims.get("role");
    }

    public String getUuidFromToken(String tokenCookie) {
        Map<String, ?> claims = getClaimsFromToken(tokenCookie);
        return (String) claims.get("sub");
    }
}
