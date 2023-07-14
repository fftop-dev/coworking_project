package ch.zli.m223.ksh20.coworking_project.controller.rest;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import ch.zli.m223.ksh20.coworking_project.controller.rest.dto.UserDto;
import ch.zli.m223.ksh20.coworking_project.controller.rest.dto.UserInputDto;
import ch.zli.m223.ksh20.coworking_project.model.Reservation;
import ch.zli.m223.ksh20.coworking_project.model.User;
import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationImpl;
import ch.zli.m223.ksh20.coworking_project.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.zli.m223.ksh20.coworking_project.controller.rest.dto.ReservationDto;
import ch.zli.m223.ksh20.coworking_project.controller.rest.dto.ReservationNoUserDto;
import ch.zli.m223.ksh20.coworking_project.security.Authorized;
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
        return reservationService.getReservationList((String)jwtTokenProvider.getClaimsFromToken(cookieToken).get("sub")).stream()
                .map(ReservationNoUserDto::new)
                .collect(Collectors.toList());
    }

    @Authorized(allowedRoles = {"ADMIN"})
    @PostMapping("/update-status/{uuid}")
    ResponseEntity<?> setReservationStatus(@CookieValue("token") String cookieToken, @PathVariable String uuid, @RequestParam String status) {
        if(reservationService.setReservationStatus(uuid, status)){
            return ResponseEntity.ok().body("success");
        }
        else{
            return ResponseEntity.badRequest().body("There was an error");
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

    @Authorized(allowedRoles = {"MEMBER", "ADMIN"})
    @DeleteMapping("/delete/{uuid}")
    ResponseEntity<?> deleteUserByUuid(@CookieValue("token") String cookieToken, @PathVariable String uuid){
        if (reservationService.checkReservationUuid(uuid, (String) jwtTokenProvider.getClaimsFromToken(cookieToken).get("sub"))){
            if(reservationService.deleteReservation(uuid)){
                return ResponseEntity.ok().body("success");
            }
        }
        return ResponseEntity.badRequest().body("There was an error");
    }

    @Authorized(allowedRoles = {"MEMBER", "ADMIN"})
    @PostMapping("/add")
    ResponseEntity<?> addReservation(@CookieValue("token") String cookieToken, @RequestParam String date, @RequestParam String type) {
        if (reservationService.addReservation((String)jwtTokenProvider.getClaimsFromToken(cookieToken).get("sub"), date, type)){
            return ResponseEntity.ok().body("success");
        }
        return ResponseEntity.badRequest().body("There was an error");
    }

}
