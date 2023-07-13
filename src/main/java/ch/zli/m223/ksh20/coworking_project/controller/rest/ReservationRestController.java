package ch.zli.m223.ksh20.coworking_project.controller.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zli.m223.ksh20.coworking_project.controller.rest.dto.ReservationDto;
import ch.zli.m223.ksh20.coworking_project.controller.rest.dto.ReservationNoUserDto;
import ch.zli.m223.ksh20.coworking_project.security.Authorized;
import ch.zli.m223.ksh20.coworking_project.service.ReservationService;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationRestController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping()
    List<ReservationNoUserDto> getReservationList() {
        // TODO: get reservations for current user
        return reservationService.getReservationList().stream()
                .map(ReservationNoUserDto::new)
                .collect(Collectors.toList());
    }

    @Authorized(allowedRoles = { "ADMIN" })
    @GetMapping("/admin")
    ResponseEntity<List<ReservationDto>> getReservationListAdmin() {
        List<ReservationDto> reservations = reservationService.getReservationList().stream()
                .map(ReservationDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(reservations);
    }
}
