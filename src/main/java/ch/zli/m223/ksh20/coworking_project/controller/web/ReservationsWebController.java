package ch.zli.m223.ksh20.coworking_project.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import ch.zli.m223.ksh20.coworking_project.controller.web.dto.Reservation;
import ch.zli.m223.ksh20.coworking_project.security.JwtToken;

@Controller
@RequestMapping("/reservations")
public class ReservationsWebController extends WebController {
    @JwtToken
    @GetMapping
    public String reservations(Model model) {
        String apiUrl = urlRoot + "/reservations";
        try {
            Reservation[] reservations = getObjects(apiUrl, Reservation[].class);
            model.addAttribute("reservations", reservations);
        } catch (HttpClientErrorException e) {
            return getErrorPage(e);
        }
        return "reservations/index";
    }

    @JwtToken
    @GetMapping("/new")
    public String newReservation(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "reservations/new";
    }

    @JwtToken
    @PostMapping("/new")
    public String createReservation(@ModelAttribute Reservation reservation, Model model) {
        String apiUrl = urlRoot + "/reservations";
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(apiUrl, reservation, Reservation.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 400) {
                model.addAttribute("error", "Reservation already exists or is invalid.");
                return "reservations/new";
            }
            return getErrorPage(e);
        }
        return "redirect:/reservations";
    }
}
