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
            // Erfolgreiche Anmeldung
            model.addAttribute("email", email);
            return "redirect:/index";
        } else {
            // Ungültige Anmeldeinformationen
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
        // Hier können Sie Ihre Registrierungs-Logik implementieren
        // Verarbeiten Sie die übermittelten Registrierungsdaten und speichern Sie sie in Ihrer Datenbank oder an anderer Stelle

        // Annahme: Registrierung erfolgreich
        model.addAttribute("firstname", firstname);
        model.addAttribute("lastname", lastname);
        model.addAttribute("email", email);

        return "redirect:/login";
    }

    @GetMapping("/index")
    public String showIndexPage(Model model) {
        // Hier können Sie weitere Modelldaten für die Indexseite hinzufügen
        return "index";
    }

    // Beispiel-Methode zum Überprüfen der Benutzeranmeldeinformationen
    private boolean isValidUser(String email, String password) {

        if (email.equals("jenith.jeyaranjan@gmail.com") && password.equals("password")) {
            return true; // Anmeldeinformationen sind gültig
        } else {
            return false; // Anmeldeinformationen sind ungültig
        }

    }

}
