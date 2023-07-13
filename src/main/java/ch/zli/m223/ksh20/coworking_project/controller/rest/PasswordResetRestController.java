package ch.zli.m223.ksh20.coworking_project.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.zli.m223.ksh20.coworking_project.service.impl.PasswordResetServiceImpl;

@RestController
@RequestMapping("/api/v1/password-reset")
public class PasswordResetRestController {

    @Autowired
    private PasswordResetServiceImpl passwordResetService;

    @PostMapping("/send-email")
    public ResponseEntity<?> sendPasswordResetEmail(@RequestParam String email) {
        try {
            passwordResetService.sendPasswordResetEmail(email);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().body("Password reset mail sent");
    }

    @PostMapping()
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String password,
            @RequestParam String confirm) {

        if (!password.equals(confirm)) {
            return ResponseEntity.badRequest().body("Passwords do not match");
        }

        try {
            passwordResetService.resetPassword(token, password);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().body("Password reset successful");
    }
}
