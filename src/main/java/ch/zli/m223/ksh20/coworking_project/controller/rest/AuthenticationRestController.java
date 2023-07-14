package ch.zli.m223.ksh20.coworking_project.controller.rest;

import java.util.Map;

import ch.zli.m223.ksh20.coworking_project.security.Authorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.zli.m223.ksh20.coworking_project.security.JwtTokenProvider;
import ch.zli.m223.ksh20.coworking_project.service.AuthenticationService;
import ch.zli.m223.ksh20.coworking_project.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationRestController {

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

    @Authorized(allowedRoles = {"MEMBER", "ADMIN"})
    @DeleteMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response, @CookieValue("token") String cookieToken) {
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok().body("Logout successful");
    }
}
