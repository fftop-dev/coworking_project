package ch.zli.m223.ksh20.coworking_project.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class AuthenticationWebController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               Model model) {
        if (isValidUser(email, password)) {
            model.addAttribute("email", email);
            return "redirect:/index";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

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

    @GetMapping("/index")
    public String showIndexPage(Model model) {
        return "index";
    }

    private boolean isValidUser(String email, String password) {

        if (email.equals("jenith.jeyaranjan@gmail.com") && password.equals("password")) {
            return true;
        } else {
            return false;
        }

    }


}
