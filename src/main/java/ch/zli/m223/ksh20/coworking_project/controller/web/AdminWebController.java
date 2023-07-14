package ch.zli.m223.ksh20.coworking_project.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import ch.zli.m223.ksh20.coworking_project.controller.web.model.User;

@Controller
@RequestMapping("/admin")
public class AdminWebController {
    @GetMapping
    public String admin() {

        return "admin/index";
    }

    @GetMapping("/users")
    public String usersList(Model model) {

        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8080/api/v1/users";

        User[] users = restTemplate.getForObject(apiUrl, User[].class);

        model.addAttribute("users", users);

        return "admin/users";
    }

    @GetMapping("/reservations")
    public String reservationsList() {
        return "admin/reservations";
    }
}
