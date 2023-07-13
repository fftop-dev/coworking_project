package ch.zli.m223.ksh20.coworking_project.controller.rest.dto;

import java.time.LocalDate;

import ch.zli.m223.ksh20.coworking_project.model.Reservation;
import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationStatus;
import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationType;

public class ReservationNoUserDto {

    public String uuid;

    public LocalDate date;
    public ReservationType type;
    public ReservationStatus status;

    public ReservationNoUserDto(Reservation reservation) {
        uuid = reservation.getUuid();
        date = reservation.getDate();
        type = reservation.getType();
        status = reservation.getStatus();
    }
}
