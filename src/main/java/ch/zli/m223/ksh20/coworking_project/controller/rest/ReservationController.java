package ch.zli.m223.ksh20.coworking_project.controller.rest;

import ch.zli.m223.ksh20.coworking_project.controller.rest.dto.ReservationDto;
import ch.zli.m223.ksh20.coworking_project.controller.rest.dto.UserDto;
import ch.zli.m223.ksh20.coworking_project.service.ReservationService;
import ch.zli.m223.ksh20.coworking_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;


    @GetMapping()
    List<ReservationDto> getReservationList() {
        return reservationService.getReservationList().stream()
                .map(ReservationDto::new)
                .collect(Collectors.toList());
    }
}
