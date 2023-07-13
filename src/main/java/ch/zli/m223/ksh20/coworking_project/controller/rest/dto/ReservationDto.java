package ch.zli.m223.ksh20.coworking_project.controller.rest.dto;

import java.time.LocalDate;

import ch.zli.m223.ksh20.coworking_project.model.Reservation;
import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationStatus;
import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationType;

public class ReservationDto extends ReservationNoUserDto {

    public UserNoReservationDto user;

    public ReservationDto(Reservation reservation) {
        super(reservation);
        user = new UserNoReservationDto(reservation.getUser());
    }
}
