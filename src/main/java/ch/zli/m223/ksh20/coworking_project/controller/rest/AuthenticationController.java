package ch.zli.m223.ksh20.coworking_project.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.zli.m223.ksh20.coworking_project.security.JwtTokenProvider;
import ch.zli.m223.ksh20.coworking_project.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {

        try {
            boolean validLogin = authenticationService.login(email, password);

            if (!validLogin) {
                return ResponseEntity.badRequest().body("Invalid password");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        // TODO: generate token

        return ResponseEntity.ok().body("Login success");
    }

}
