package ch.zli.m223.ksh20.coworking_project.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/password-reset")
public class PasswordResetWebController {
    @GetMapping
    public String get() {
        return "password-reset/index";
    }

    @GetMapping("/send")
    public String getSend() {
        return "password-reset/send";
    }

    @GetMapping("/success")
    public String getSuccess() {
        return "password-reset/success";
    }
}
