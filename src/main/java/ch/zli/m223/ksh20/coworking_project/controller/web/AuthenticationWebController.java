package ch.zli.m223.ksh20.coworking_project.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.zli.m223.ksh20.coworking_project.controller.web.dto.LoginForm;
import ch.zli.m223.ksh20.coworking_project.controller.web.dto.RegisterForm;
import ch.zli.m223.ksh20.coworking_project.security.JwtToken;

@Controller
@RequestMapping("/")
public class AuthenticationWebController extends WebController {

    @JwtToken
    @GetMapping("/login")
    public String showLoginForm() {
        return "user/login";
    }

    @JwtToken
    @PostMapping("/login")
    public String processLogin(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
        if (loginUser(loginForm)) {
            return "redirect:/index";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "user/login";
        }
    }

    @JwtToken
    @GetMapping("/register")
    public String showRegisterForm() {
        return "user/register";
    }

    @JwtToken
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("registerForm") RegisterForm registerForm, Model model) {

        if (registerUser(registerForm) == 200) {
            return "redirect:/index";
        } else if (registerUser(registerForm) == 400) {
            model.addAttribute("error", "Invalid Registration");
            return "user/register";
        } else if (registerUser(registerForm) == 409) {
            model.addAttribute("error", "Email already exists");
            return "user/register";
        }

        return "redirect:/user/login";
    }

    @JwtToken
    @GetMapping("/index")
    public String showIndexPage(Model model) {
        return "index";
    }

    private boolean loginUser(LoginForm loginData) {
        var data = new LinkedMultiValueMap<String, String>();
        data.add("password", loginData.getPassword());
        data.add("email", loginData.getEmail());

        try {
            sendPostRequest("/auth/login", data, String.class);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private int registerUser(RegisterForm registerForm) {
        var data = new LinkedMultiValueMap<String, String>();
        data.add("password", registerForm.getPassword());
        data.add("email", registerForm.getEmail());
        data.add("firstName", registerForm.getFirstName());
        data.add("lastName", registerForm.getLastName());

        try {
            sendPostRequest("/auth/register", data, String.class);
        } catch (Exception e) {
            if (e.getMessage().contains("400")) {
                return 400;
            } else if (e.getMessage().contains("409")) {
                return 409;
            } else {
                return 500;
            }
        }
        return 200;
    }

}
