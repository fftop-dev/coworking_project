package ch.zli.m223.ksh20.coworking_project.controller.web;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import ch.zli.m223.ksh20.coworking_project.controller.web.model.LoginForm;
import ch.zli.m223.ksh20.coworking_project.security.JwtToken;

@Controller
@RequestMapping("/")
public class AuthenticationWebController extends WebController {

    @JwtToken
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @JwtToken
    @PostMapping("/login")
    public String processLogin(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
        model.addAttribute("email", loginForm.getEmail());
        model.addAttribute("password", loginForm.getPassword());

        if (isValidUser(loginForm)) {
            return "redirect:/index";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

    @JwtToken
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @JwtToken
    @PostMapping("/register")
    public String processRegistration(@RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model) {
        model.addAttribute("firstname", firstname);
        model.addAttribute("lastname", lastname);
        model.addAttribute("email", email);

        return "redirect:/login";
    }

    @JwtToken
    @GetMapping("/index")
    public String showIndexPage(Model model) {
        return "index";
    }

    private boolean isValidUser(LoginForm loginData) {
        String apiUrl = urlRoot + "/auth/login";

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Create HTTP headers with JSON content type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // add data
        var data = new LinkedMultiValueMap<String, String>();
        data.add("password", loginData.getPassword());
        data.add("email", loginData.getEmail());
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(data, headers);

        // Send the POST request to the REST endpoint
        try {
            restTemplate.postForEntity(apiUrl, request, String.class);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
