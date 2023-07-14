package ch.zli.m223.ksh20.coworking_project.controller.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.zli.m223.ksh20.coworking_project.controller.rest.dto.ReservationDto;
import ch.zli.m223.ksh20.coworking_project.controller.rest.dto.ReservationNoUserDto;
import ch.zli.m223.ksh20.coworking_project.security.Authorized;
import ch.zli.m223.ksh20.coworking_project.security.JwtTokenProvider;
import ch.zli.m223.ksh20.coworking_project.service.ReservationService;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationRestController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Authorized(allowedRoles = { "MEMBER", "ADMIN" })
    @GetMapping()
    List<ReservationNoUserDto> getReservationList(@CookieValue("token") String cookieToken) {
        return reservationService
                .getReservationList((String) jwtTokenProvider.getClaimsFromToken(cookieToken).get("sub")).stream()
                .map(ReservationNoUserDto::new)
                .collect(Collectors.toList());
    }

    @Authorized(allowedRoles = { "ADMIN" })
    @PostMapping("/update-status/{uuid}")
    ResponseEntity<?> setReservationStatus(@CookieValue("token") String cookieToken, @PathVariable String uuid,
            @RequestParam String status) {
        if (reservationService.setReservationStatus(uuid, status)) {
            return ResponseEntity.ok().body("status changed");
        } else {
            return ResponseEntity.badRequest().body("there was an error");
        }
    }

    @Authorized(allowedRoles = { "ADMIN" })
    @GetMapping("/admin")
    ResponseEntity<List<ReservationDto>> getReservationListAdmin() {
        List<ReservationDto> reservations = reservationService.getReservationList().stream()
                .map(ReservationDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(reservations);
    }

    @Authorized(allowedRoles = { "MEMBER", "ADMIN" })
    @DeleteMapping("/delete/{uuid}")
    ResponseEntity<?> deleteUserByUuid(@CookieValue("token") String cookieToken, @PathVariable String uuid) {
        if (reservationService.checkReservationUuid(uuid,
                (String) jwtTokenProvider.getClaimsFromToken(cookieToken).get("sub"))) {
            if (reservationService.deleteReservation(uuid)) {
                return ResponseEntity.ok().body("Reservation [" + uuid + "] has been deleted successfully");
            }
        }
        return ResponseEntity.ok().body("There was an error");
    }

    @Authorized(allowedRoles = { "MEMBER", "ADMIN" })
    @PostMapping("/add")
    ResponseEntity<?> addReservation(@CookieValue("token") String cookieToken, @RequestParam String date,
            @RequestParam String type) {
        if (reservationService.addReservation((String) jwtTokenProvider.getClaimsFromToken(cookieToken).get("sub"),
                date, type)) {
            return ResponseEntity.ok().body("Reservation has been added successfully");
        }
        return ResponseEntity.ok().body("There was an error");
    }

    @Authorized(allowedRoles = { "ADMIN" })
    @GetMapping("/stats")
    ResponseEntity<?> getReservationStats() {
        return ResponseEntity.ok().body(reservationService.getReservationStats());
    }

}
