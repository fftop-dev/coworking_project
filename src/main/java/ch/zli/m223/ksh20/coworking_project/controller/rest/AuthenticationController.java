package ch.zli.m223.ksh20.coworking_project.controller.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.zli.m223.ksh20.coworking_project.security.JwtTokenProvider;
import ch.zli.m223.ksh20.coworking_project.service.AuthenticationService;
import ch.zli.m223.ksh20.coworking_project.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    HttpServletRequest request;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password,
            HttpServletResponse response) {

        try {
            boolean validLogin = authenticationService.login(email, password);

            if (!validLogin) {
                return ResponseEntity.badRequest().body("Invalid password");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        String token = jwtTokenProvider.generateToken(userService.getUserByEmail(email));

        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(60); // seconds
        cookie.setPath("/");

        response.addCookie(cookie);

        return ResponseEntity.ok().body("Login successful");
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(@CookieValue("token") String cookieToken) {

        Map<String, ?> claims = jwtTokenProvider.getClaimsFromToken(cookieToken);

        return ResponseEntity.ok().body(claims);
    }
}
