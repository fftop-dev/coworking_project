package ch.zli.m223.ksh20.coworking_project.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

import ch.zli.m223.ksh20.coworking_project.controller.web.model.Reservation;
import ch.zli.m223.ksh20.coworking_project.controller.web.model.User;
import ch.zli.m223.ksh20.coworking_project.security.JwtToken;

@Controller
@RequestMapping("/admin")
public class AdminWebController extends WebController {
    @JwtToken
    @GetMapping
    public String admin() {
        return "admin/index";
    }

    @JwtToken
    @GetMapping("/users")
    public String usersList(Model model) {
        String apiUrl = urlRoot + "/users";
        try {
            User[] users = getObjects(apiUrl, User[].class);
            model.addAttribute("users", users);
        } catch (HttpClientErrorException e) {
            return getErrorPage(e);
        }
        return "admin/users";
    }

    @JwtToken
    @GetMapping("/reservations")
    public String reservationsList(Model model) {
        String apiUrl = urlRoot + "/reservations/admin";
        try {
            Reservation[] reservations = getObjects(apiUrl, Reservation[].class);
            model.addAttribute("reservations", reservations);
        } catch (HttpClientErrorException e) {
            return getErrorPage(e);
        }
        return "admin/reservations";
    }
}
